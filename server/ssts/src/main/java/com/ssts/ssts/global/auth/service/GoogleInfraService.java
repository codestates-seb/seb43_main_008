package com.ssts.ssts.global.auth.service;


import com.ssts.ssts.global.auth.api.GoogleApi;
import com.ssts.ssts.global.auth.api.GoogleAuthApi;
import com.ssts.ssts.global.auth.utils.AuthConsts;
import com.ssts.ssts.global.auth.dto.GoogleProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleInfraService {
    private final GoogleApi googleApi;
    private final GoogleAuthApi googleAuthApi;

    public static final String EMPTY_STRING = "";

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    //요청 보내기
    public String getAccessToken(String code) {
        String str=googleAuthApi.getAccessToken(code, clientId, clientSecret, AuthConsts.REDIRECT_URL+"google", "authorization_code", EMPTY_STRING).getAccessToken();
        log.info("하늘/oauth google infra service -> get access token() : \n"+
                "access token="+str);
        return str;
    }

    public GoogleProfileResponse getGoogleAccount(String accessToken) {
        GoogleProfileResponse response= googleApi.getGoogleUser("Bearer".concat(" " + accessToken));

        log.info("하늘/oauth google infra service -> get google account : \n"+
                "id="+response.getId()+
                ", email="+response.getEmail());

        return response;
    }

}
