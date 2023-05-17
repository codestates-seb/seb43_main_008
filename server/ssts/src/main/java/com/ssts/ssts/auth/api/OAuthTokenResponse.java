package com.ssts.ssts.auth.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthTokenResponse {
    String accessToken;
    String refreshToken;
    String email;

    public static OAuthTokenResponse of(String accessToken, String refreshToken, String email) {
        OAuthTokenResponse tokenResponse=new OAuthTokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setEmail(email);
        return tokenResponse;
    }
}
