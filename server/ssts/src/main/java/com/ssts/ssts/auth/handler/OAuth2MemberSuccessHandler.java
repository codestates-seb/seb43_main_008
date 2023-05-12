package com.ssts.ssts.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ssts.ssts.auth.jwt.JwtTokenizer;
import com.ssts.ssts.auth.utils.CustomOAuth2User;
import com.ssts.ssts.auth.utils.GuestObject;
import com.ssts.ssts.auth.utils.GuestObjectSerializer;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.security.core.GrantedAuthority;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Slf4j
public class OAuth2MemberSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenizer jwtTokenizer;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("하늘/security : SuccessHandler 진입");

        var customOAuth2User = (CustomOAuth2User)authentication.getPrincipal();
        //Authentication -> CustomOAuth2User객체 가져오기

        // CustomOAuth2User객체에서 데이터(email, id) 뽑기
        String email = customOAuth2User.getEmail();
        long id = customOAuth2User.getMemberId();
        log.info("하늘/security : email="+email+", id="+id);

        // 권한 정보 가져오기
        List<String> authorities = customOAuth2User.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        log.info("하늘/security : authorities="+authorities.toString());

        // 비회원일 경우 (role_guest) -> json_body 에 email 담아서 응답
        // FIXME token을 암호화해서 쿠키로 발급해야한다.
        if (authorities.toString().equals("[ROLE_GUEST]")) {

            GuestObject object = new GuestObject(email);
            String json = "";

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addSerializer(GuestObject.class, new GuestObjectSerializer());
                objectMapper.registerModule(module);
                json = objectMapper.writeValueAsString(object);
            } catch (IOException e) {
                throw new BusinessLogicException(ExceptionCode.SECURITY_GUEST_OBJECT_SERIALIZE_ERROR);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);

            try {
                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            // 회원일 경우 (role_user/admin) -> 토큰 발급
            redirect(request, response, id, email, authorities); // 권한 정보 받아서 새 요청 만들기
        }

    }


    //TODO 왜 IOException을 던지는걸까? 이유 찾아보기.
    private void redirect(HttpServletRequest request, HttpServletResponse response, long id, String email, List<String> authorities) throws IOException {

        System.out.println("하늘/security : redirect() 메서드 진입");

        String accessToken = delegateAccessToken(id, email, authorities);  // access token 생성
        String refreshToken = delegateRefreshToken(email);     // refresh token 생성
        log.info("하늘/security Bearer {}" , accessToken);

        response.addHeader("Authorization", "Bearer " + accessToken);
        response.addHeader("Refresh", refreshToken);

        response.setStatus(HttpServletResponse.SC_OK);
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

    private URI createURI() {

        // 로그인 성공하면 홈 화면으로 리다이렉트
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .path("/")
                .build()
                .toUri();
    }
}
