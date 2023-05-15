package com.ssts.ssts.global.auth.api;


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

    private String redirectUrl="http://localhost:8080/login/oauth2/code/google";

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    //요청 보내기
    public String getAccessToken(String code) {
        String str=googleAuthApi.getAccessToken(code, clientId, clientSecret, redirectUrl, "authorization_code", EMPTY_STRING).getAccessToken();
        log.info("하늘/oauth google infra service -> get access token() : \n"+
                "access token="+str);
        return str;
        //GoogleTokenResponse.getAccessToken()
    }

    public GoogleProfileResponse getGoogleAccount(String accessToken) {
        GoogleProfileResponse response= googleApi.getGoogleUser("Bearer".concat(" " + accessToken));

        log.info("하늘/oauth google infra service -> get google account : \n"+
                "id="+response.getId()+
                ", email="+response.getEmail());

        return response;
    }

}
