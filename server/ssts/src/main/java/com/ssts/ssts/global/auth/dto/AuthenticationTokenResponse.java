package com.ssts.ssts.global.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String nickName;

    public static AuthenticationTokenResponse of(String accessToken,
                     String refreshToken,
                     String nickName) {

        AuthenticationTokenResponse response=new AuthenticationTokenResponse();

        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setNickName(nickName);

        return response;
    }
}
