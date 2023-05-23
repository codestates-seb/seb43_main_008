package com.ssts.ssts.global.auth.api;

import com.ssts.ssts.global.auth.dto.NaverTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver-auth", url = "https://nid.naver.com")
public interface NaverAuthApi {

    @PostMapping("/oauth2.0/token")
    NaverTokenResponse getAccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("code") String code);
}
