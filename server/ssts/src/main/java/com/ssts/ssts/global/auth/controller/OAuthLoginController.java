package com.ssts.ssts.global.auth.controller;

import com.ssts.ssts.global.auth.api.OAuthService;
import com.ssts.ssts.global.auth.api.OAuthTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class OAuthLoginController {

    private String googleUrl="https://accounts.google.com";
    private String kakaoUrl="/oauth2/authorization/kakao";

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    private String googleScope="email%20profile";
    private String kakaoScope="account_email";
    private String redirectUrl="http://ec2-3-37-46-164.ap-northeast-2.compute.amazonaws.com:8080/login/oauth2/code/";

    private final String responseType = "code";
    //private String authRequestUrl;

    private final OAuthService oAuthService;

    @GetMapping("/google")
    public void redirectGoogle(HttpServletResponse response) throws IOException {
        log.info("하늘/oauth login : google");

        UriComponents uri = UriComponentsBuilder.fromUriString(googleUrl)
                .pathSegment("o", "oauth2", "v2", "auth")
                .queryParam("response_type", responseType)
                .queryParam("client_id", googleClientId)
                .queryParam("redirect_uri", redirectUrl+"google")
                .queryParam("scope", googleScope)
                .build();

        response.sendRedirect(uri.toString());
    }

    @GetMapping("/oauth2/code/google")
    public void callbackGoogle(HttpServletResponse response, @RequestParam(name = "code") String code) throws IOException{
        log.info("하늘/oauth login callback: google\n"+
                "code="+code);
        OAuthTokenResponse tokenResponse=oAuthService.login(code);

        System.out.println("하늘/oauth oauth service : redirect");

        if(tokenResponse.getAccessToken()==null){
            //FIXME 비회원이면 토큰에 암호화해서 담고 쿠키 발급하기
            response.addHeader("email", tokenResponse.getEmail());
            response.setStatus(HttpServletResponse.SC_OK);

            Cookie cookie = new Cookie("email", tokenResponse.getEmail());
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            response.addCookie(cookie);


            response.sendRedirect("http://localhost:3000/register");



        }else{

            Cookie cookie = new Cookie("accessToken", tokenResponse.getAccessToken());
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            response.addHeader("Authorization", "Bearer " + tokenResponse.getAccessToken());
            response.addHeader("Refresh", tokenResponse.getRefreshToken());
            response.setStatus(HttpServletResponse.SC_OK);

            response.sendRedirect("http://localhost:3000/");
        }


    }

    @GetMapping("/kakao")
    public void redirectKakao(HttpServletResponse response) throws IOException {
        log.info("하늘/oauth login : kakao");

        UriComponents uri = UriComponentsBuilder.fromUriString(kakaoUrl)
                .pathSegment("o", "oauth2", "v2", "auth")
                .queryParam("response_type", responseType)
                .queryParam("client_id", kakaoClientId)
                .queryParam("redirect_uri", redirectUrl+"kakao")
                .queryParam("scope", kakaoScope)
                .build();


        response.sendRedirect(uri.toString());
    }
}
