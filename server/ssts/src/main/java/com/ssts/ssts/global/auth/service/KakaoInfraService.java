package com.ssts.ssts.global.auth.service;

import com.ssts.ssts.global.auth.api.KakaoApi;
import com.ssts.ssts.global.auth.api.KakaoAuthApi;
import com.ssts.ssts.global.auth.utils.AuthConsts;
import com.ssts.ssts.global.auth.dto.KakaoProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoInfraService {
    private final KakaoApi kakaoApi;
    private final KakaoAuthApi kakaoAuthApi;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    public String getAccessToken(String code) {
        return kakaoAuthApi.getAccessToken("authorization_code", clientId, AuthConsts.REDIRECT_URL+"kakao", code, clientSecret).getAccessToken();
    }

    public KakaoProfileResponse getKakaoAccount(String accessToken) {
        return kakaoApi.getKakaoUser("Bearer".concat(" " + accessToken));
    }
}
