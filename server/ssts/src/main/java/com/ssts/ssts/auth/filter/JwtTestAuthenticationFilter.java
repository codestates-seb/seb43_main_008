package com.ssts.ssts.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssts.ssts.auth.dto.TestLoginDto;
import com.ssts.ssts.auth.jwt.JwtTokenizer;
import com.ssts.ssts.auth.utils.CustomAuthorityUtils;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class JwtTestAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;


    // 인증을 시도하는 로직 -> Test용이라서 email만 DB에 등록되어 있어도 인증이 성공한다.
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        log.info("하늘 security : jwt test authentication filter");
        // 클라이언트 -> 서버 역직렬화(json->object)
        ObjectMapper objectMapper = new ObjectMapper();
        TestLoginDto testLoginDto = objectMapper.readValue(request.getInputStream(), TestLoginDto.class);
        // ServletInputStream -> TestLoginDto(역직렬화)
        log.info("하늘 security : 입력 email="+testLoginDto.getEmail());
        Member member = memberService.findMemberByEmail(testLoginDto.getEmail());

        Map<String, Object> credentials=new HashMap<>();
        credentials.put("id",member.getId());

        List<GrantedAuthority> authorityList=authorityUtils.createAuthorities(member.getRoles());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(member.getEmail(),credentials, authorityList);

        return authenticationToken;
    }

    // 인증에 성공할 경우 호출된다.
    // AbstractAuthenticationProcessingFilter에서 호출한다고 뜬다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws ServletException, IOException {

        log.info("하늘/security : jwt test authentication filter -> success ");

        String email = (String) authResult.getPrincipal();
        Map<String, Object> credentials =  (Map<String, Object>)authResult.getCredentials();
        long id =(long)credentials.get("id");

        log.info("하늘/security : id="+id+", email="+email);
        List<String> authorities = authResult.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String accessToken = delegateAccessToken(id, email, authorities);
        String refreshToken = delegateRefreshToken(email);

        log.info("하늘/security : Bearer " + accessToken);
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);

        //Security Configuration에서 .setAuthenticationSuccessHandler 핸들러를 설정한다.
        //핸들러의 메서드 호출한다.
        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }


    private String delegateAccessToken(long id, String email, List<String> authorities) {

        // 1.subject = email (principal)
        String subject = email;

        // 2.claim = id, roles
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("roles", authorities);

        // claims 구성 확인
        // FIXME 나중에 스트림으로 처리하기
        String claimsStr="";
        Iterator<String> keys=claims.keySet().iterator();
        while(keys.hasNext()){
            String key=keys.next();
            claimsStr+="["+key+"]="+claims.get(key).toString()+"\n";
        }
        log.info("하늘/jwt(userpassword) : claims=\n"+ claimsStr);

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
