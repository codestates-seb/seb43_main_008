package com.ssts.ssts.auth.handler;

import com.ssts.ssts.auth.jwt.JwtTokenizer;
import com.ssts.ssts.auth.utils.CustomAuthorityUtils;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberOAuthService;
import com.ssts.ssts.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RequiredArgsConstructor
@Slf4j
public class OAuth2MemberSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var oAuth2User = (OAuth2User)authentication.getPrincipal();
        //Authentication -> OAuth2User -> 이메일 주소 접근
        String email = String.valueOf(oAuth2User.getAttributes().get("email"));
        log.info("email="+email);

        // 회원가입시에는 이메일 중복체크 필요하다
        // memberService.verifyExistsEmail(email);

        // 지금은 로그인 구현중인 거니까
        // 존재하는 멤버인지 확인 작업 필요하다.
        // Member member = memberOAuthService.findMemberByEmail(email);
        List<String> authorities = List.of("USER");
        // DB -> member 객체에서 권한정보 가져오기
        log.info("authorities="+authorities.toString());

        redirect(request, response, email, authorities); // 권한 정보 받아서 새 요청 만들기
    }


    //FIXME 왜 IOException을 던지는걸까? 이유 찾아보기.
    private void redirect(HttpServletRequest request, HttpServletResponse response, String email, List<String> authorities) throws IOException {

        String accessToken = delegateAccessToken(email, authorities);  // access token 생성
        String refreshToken = delegateRefreshToken(email);     // refresh token 생성
        System.out.println("하늘: redirect 실행");
        log.info("Bearer {}" , accessToken);
        response.addHeader("Authorization", "Bearer " + accessToken);
        // response header(Authorization)에 access token을 추가한다. (첫인증 후 응답할때만)
        // 클라이언트는 이후에 request할때마다 request header에 해당 토큰을 추가해서 클라이언트 측의 자격을 증명한다.
        response.addHeader("Refresh", refreshToken);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    private String delegateAccessToken(String email, List<String> authorities) {
        //email과 권한 받아옴
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        //id로 다뤄야할까 email로 다뤄야할까? email도 중복저장안되긴해서 식별자 가능하긴 한데.
        //claims.put("id", memberId);
        //id 넣으려고하니까, email로 멤버 검색해서 가져와야한다. 너무 다양한 곳에서 끌어써서 별로인듯.
        claims.put("roles", authorities);
        //JWT에 넣을 클레임 생성하기 -> 암호화되나? 안된다.
        //id넣을 거면 claims 암호화하기.
        //email넣을 거면 그냥 넣고~


        String subject = email;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        //JWT에 넣을 만기 만들기

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        //(string->base64) key

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
        //access token만들기
        //질문) subject는 어디에 쓰는 건데?
        // -> subject은 정보의 주체를 나타내는 속성이다. id나 email, 즉 식별자를 넣으면 된다. 반드시 필요
        // 그런데 보통 문자열 타입을 넣는다네, 그럼 email넣자.

        return accessToken;
    }

    private String delegateRefreshToken(String username) {

        //access token과는 다르게 claims를 넣지 않는다.

        String subject = username;
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
