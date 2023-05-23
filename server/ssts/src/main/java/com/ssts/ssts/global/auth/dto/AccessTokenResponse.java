package com.ssts.ssts.global.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccessTokenResponse {
    String accessToken;
    String email;
    boolean Authenticated;

    public static AccessTokenResponse of(String accessToken, String email, boolean isAuthenticated) {
        AccessTokenResponse tokenResponse=new AccessTokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setEmail(email);
        tokenResponse.setAuthenticated(isAuthenticated);
        return tokenResponse;
    }
}
