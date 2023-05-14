package com.ssts.ssts.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/login")
@Slf4j
public class OAuthLoginController {

    private String googleUrl="https://accounts.google.com";
    private String kakaoUrl="/oauth2/authorization/kakao";

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    //@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    private String googleScope="email profile";
    private String kakaoScope="account_email";


    private String redirectUrl="http://localhost:8080/login/oauth2/code/";


    private final String responseType = "code";

    //private String authRequestUrl;


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

    @GetMapping("/kakao")
    public void redirectKakao(HttpServletResponse response) throws IOException {
        log.info("하늘/oauth login : kakao");

        UriComponents uri = UriComponentsBuilder.fromUriString(kakaoUrl)
                .pathSegment("oauth", "authorize")
                .queryParam("response_type", responseType)
                .queryParam("client_id", kakaoClientId)
                .queryParam("redirect_uri", redirectUrl+"kakao")
                .build();


        response.sendRedirect(uri.toString());
    }


}
