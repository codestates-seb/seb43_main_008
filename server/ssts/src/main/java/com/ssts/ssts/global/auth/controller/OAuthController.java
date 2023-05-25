package com.ssts.ssts.global.auth.controller;

import com.ssts.ssts.global.auth.utils.AuthConsts;
import com.ssts.ssts.global.auth.dto.AccessTokenResponse;
import com.ssts.ssts.global.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String naverClientId;

    private String googleScope="email%20profile";
    private String redirectUrl="http://ec2-3-37-46-164.ap-northeast-2.compute.amazonaws.com:8080/login/oauth2/code/";

    private final String responseType = "code";

    private final OAuthService oauthService;

    @GetMapping("/login/google")
    public void redirectGoogle(HttpServletResponse response) throws IOException {
        log.info("하늘/oauth redirect : google");

        UriComponents uri = UriComponentsBuilder.fromUriString(AuthConsts.GOOGLE_URL)
                .pathSegment("o", "oauth2", "v2", "auth")
                .queryParam("response_type", responseType)
                .queryParam("client_id", googleClientId)
                .queryParam("redirect_uri", AuthConsts.REDIRECT_URL+"google")
                .queryParam("scope", googleScope)
                .build();

        response.sendRedirect(uri.toString());
    }

    @GetMapping("/login/kakao")
    public void redirectKakao(HttpServletResponse response) throws IOException {
        log.info("하늘/oauth redirect : kakao");

        UriComponents uri = UriComponentsBuilder.fromUriString(AuthConsts.KAKAO_URL)
                .pathSegment("oauth", "authorize")
                .queryParam("response_type", responseType)
                .queryParam("client_id", kakaoClientId)
                .queryParam("redirect_uri", AuthConsts.REDIRECT_URL+"kakao")
                .build();


        response.sendRedirect(uri.toString());
    }

    @GetMapping("/login/naver")
    public void redirectNaver(HttpServletResponse response) throws IOException {
        log.info("하늘/oauth redirect : naver");

        UriComponents uri = UriComponentsBuilder.fromUriString(AuthConsts.NAVER_URL)
                .pathSegment("oauth2.0", "authorize")
                .queryParam("response_type", responseType)
                .queryParam("client_id", naverClientId)
                .queryParam("redirect_uri", AuthConsts.REDIRECT_URL+"naver")
                .build();

        response.sendRedirect(uri.toString());

    }


    @GetMapping("/login/oauth2/code/{socialType}")
    public void callbackGoogle(HttpServletResponse response, @RequestParam(name = "code") String code, @PathVariable String socialType) throws IOException{
        log.info("하늘/oauth redirect callback :" +
                "\ncode="+code);

        AccessTokenResponse tokenResponse=oauthService.accessResources(code, socialType);

        log.info("하늘/oauth token response :\n"+
                tokenResponse.toString());

        UriComponents uri;

        if (tokenResponse.isAuthenticated()) {
            uri = UriComponentsBuilder.fromUriString(AuthConsts.FE_BASE_URL) //FIXME url 수정 필요
                    .queryParam("Access",tokenResponse.getAccessToken())
                    .build();
        }else{
            uri = UriComponentsBuilder.fromUriString(AuthConsts.FE_BASE_URL+"/signup") //FIXME url 수정 필요
                    .queryParam("Access",tokenResponse.getAccessToken())
                    .queryParam("email",tokenResponse.getEmail())
                    .build();
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect(uri.toString());

    }





}
