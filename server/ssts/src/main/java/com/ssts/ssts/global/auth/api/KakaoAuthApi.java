package com.ssts.ssts.global.auth.api;

import com.ssts.ssts.global.auth.dto.KakaoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao-auth", url="https://kauth.kakao.com")
public interface KakaoAuthApi {
    @GetMapping("/oauth/authorize")
    void getAuthCode(@RequestParam("client_id") String clientId, @RequestParam("redirect_uri") String redirectUri, @RequestParam("response_type") String responseType);


    @PostMapping("/oauth/token")
    KakaoTokenResponse getAccessToken(
            @RequestParam("grant_type") String grantType
            , @RequestParam("client_id") String clientId
            , @RequestParam("redirect_uri") String redirectUri
            , @RequestParam("code") String code
            , @RequestParam("client_secret") String clientSecret);
}
