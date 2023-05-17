package com.ssts.ssts.global.auth.api;

import com.ssts.ssts.global.auth.jwt.JwtTokenizer;
import com.ssts.ssts.global.auth.utils.CustomAuthorityUtils;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

    private final GoogleInfraService googleInfraService;
    private final MemberService memberService;
    private final CustomAuthorityUtils authorityUtils;
    private final JwtTokenizer jwtTokenizer;

    public void login(HttpServletResponse response, String code) throws IOException {

        log.info("하늘/oauth oauth service : login()");
        GoogleProfileResponse googleProfileResponse=googleInfraService.getGoogleAccount(googleInfraService.getAccessToken(code));

        Optional<Member> member= memberService.findMemberByEmail(googleProfileResponse.getEmail());

        // 회원이면
        if(member.isPresent()){
            long id= member.get().getId();
            String email = member.get().getEmail();

            log.info("하늘/oauth oauth service : " +
                    "\nid="+id+
                    "\nemail="+email+
                    "\nroles="+member.get().getRoles().toString());

            List<String> authorityList = authorityUtils.createAuthorities(member.get().getRoles())
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());


            String accessToken = delegateAccessToken(id, email, authorityList);  // access token 생성
            String refreshToken = delegateRefreshToken(email);     // refresh token 생성
            log.info("하늘/security Bearer {}" , accessToken);

            response.addHeader("Authorization", "Bearer " + accessToken);
            response.addHeader("Refresh", refreshToken);

            Cookie cookie = new Cookie("Authorization", accessToken);
            cookie.setHttpOnly(false);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(1000);
            //response.addCookie(cookie);

            response.setHeader("Set-Cookie", "Authorization=Bearer " + accessToken+"; Secure; SameSite=None");
            response.addHeader("Set-Cookie", "Refresh="+refreshToken+"; Secure; SameSite=None");

            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("http://localhost:3000/");
            //response.sendRedirect("http://localhost:8080/");

        }
        else{

            String email= googleProfileResponse.getEmail();
            response.addHeader("email", email);
            response.setStatus(HttpServletResponse.SC_OK);

            Cookie cookie2 = new Cookie("email", email);
            cookie2.setHttpOnly(false);
            cookie2.setSecure(true);
            cookie2.setPath("/");
            cookie2.setMaxAge(1000);
            //response.addCookie(cookie2);
            response.setHeader("Set-Cookie", "email="+email+"; Secure; SameSite=None");

            response.sendRedirect("http://localhost:3000/register");

        }



    }

    //FIXME
    private String delegateAccessToken(long id, String email, List<String> authorities) {

        // 1.subject = email (principal)
        String subject = email;

        // 2.claim = id, roles
        // FIXME 나중에 스트림으로 처리하기
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authorities);
        claims.put("id", id);

        // claims 구성 확인
        String claimsStr="";
        Iterator<String> keys=claims.keySet().iterator();
        while(keys.hasNext()){
            String key=keys.next();
            claimsStr+="["+key+"]="+claims.get(key).toString()+"\n";
        }
        log.info("하늘/jwt(oauth) : claims=\n"+ claimsStr);

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(String email) {

        String subject = email;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
