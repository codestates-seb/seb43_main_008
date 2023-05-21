package com.ssts.ssts.global.auth.controller;

import com.ssts.ssts.domain.member.dto.MemberSignUpAddInfoDto;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.global.auth.constansts.TestConstants;
import com.ssts.ssts.global.auth.dto.OAuthTokenResponse;
import com.ssts.ssts.global.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class OAuthController {

    private String googleUrl="https://accounts.google.com";
    private String kakaoUrl="/oauth2/authorization/kakao";

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    //@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    private String googleScope="email%20profile";
    private String kakaoScope="account_email";
    //private String redirectUrl="http://ec2-3-37-46-164.ap-northeast-2.compute.amazonaws.com:8080/login/oauth2/code/";
    //private String redirectUrl="http://localhost:8080/login/oauth2/code/";



    private final String responseType = "code";
    //private String authRequestUrl;

    private final OAuthService oauthService;

    @GetMapping("/login/google")
    public void redirectGoogle(HttpServletResponse response) throws IOException {
        log.info("하늘/oauth redirect : google");

        UriComponents uri = UriComponentsBuilder.fromUriString(googleUrl)
                .pathSegment("o", "oauth2", "v2", "auth")
                .queryParam("response_type", responseType)
                .queryParam("client_id", googleClientId)
                .queryParam("redirect_uri", TestConstants.SERVER_GOOGLE_REDIRECT_URL)
                .queryParam("scope", googleScope)
                .build();

        response.sendRedirect(uri.toString());
    }

    @GetMapping("/login/oauth2/code/google")
    public void callbackGoogle(@RequestParam(name = "code") String code,HttpServletResponse response) throws IOException{
        log.info("하늘/oauth redirect callback : google" +
                "\ncode="+code);

        OAuthTokenResponse tokenResponse=oauthService.accessResources(code);

        log.info("하늘/oauth token response :\n"+
                tokenResponse.toString());

        UriComponents uri;

        if (tokenResponse.isAuthenticated()) {
            uri = UriComponentsBuilder.fromUriString(TestConstants.FE_BASE_URL) //FIXME url 수정 필요
                    .queryParam("Access",tokenResponse.getAccessToken())
                    .build();
        }else{
            uri = UriComponentsBuilder.fromUriString(TestConstants.FE_BASE_URL2) //FIXME url 수정 필요
                    .queryParam("Access",tokenResponse.getAccessToken())
                    .queryParam("email",tokenResponse.getEmail())
                    .build();
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect(uri.toString());

    }

/*
    @GetMapping("/login/kakao")
    public void redirectKakao(HttpServletResponse response) throws IOException {
        log.info("하늘/oauth redirect : kakao");

        UriComponents uri = UriComponentsBuilder.fromUriString(kakaoUrl)
                .pathSegment("oauth", "authorize")
                .queryParam("response_type", responseType)
                .queryParam("client_id", kakaoClientId)
                .queryParam("redirect_uri", redirectUrl+"kakao")
                .build();

        response.sendRedirect(uri.toString());
    }*/


}
