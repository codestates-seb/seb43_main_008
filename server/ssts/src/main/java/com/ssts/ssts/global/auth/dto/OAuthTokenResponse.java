package com.ssts.ssts.global.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OAuthTokenResponse {
    String accessToken;
    String refreshToken;
    String email;
    String redirectUrl;
    boolean isAuthenticated;

    public static OAuthTokenResponse of(String accessToken, String refreshToken) {
        OAuthTokenResponse tokenResponse=new OAuthTokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return tokenResponse;
    }

    //
    public static OAuthTokenResponse of(String accessToken, String email, String redirectUrl, boolean isAuthenticated) {
        OAuthTokenResponse tokenResponse=new OAuthTokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setEmail(email);
        tokenResponse.setRedirectUrl(redirectUrl);
        tokenResponse.setAuthenticated(isAuthenticated);
        return tokenResponse;
    }
}
