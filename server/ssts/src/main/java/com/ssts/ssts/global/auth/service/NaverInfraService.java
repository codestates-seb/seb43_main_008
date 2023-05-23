package com.ssts.ssts.global.auth.service;

import com.ssts.ssts.global.auth.api.NaverApi;
import com.ssts.ssts.global.auth.api.NaverAuthApi;
import com.ssts.ssts.global.auth.dto.NaverProfileResponse;
import com.ssts.ssts.global.auth.dto.NaverTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NaverInfraService {

    private final NaverApi naverApi;
    private final NaverAuthApi naverAuthApi;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String clientSecret;

    public String getAccessToken(String code){
        return naverAuthApi.getAccessToken("authorization_code",clientId,clientSecret,code).getAccessToken();
    }

    public NaverProfileResponse getNaverAccount(String accessToken) {
        return naverApi.getNaverUser("Bearer".concat(" " + accessToken));
    }

}
