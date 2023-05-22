package com.ssts.ssts.global.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String nickName;

    public static LoginResponse of(String nickName){
        LoginResponse response=new LoginResponse();
        response.setNickName(nickName);
        return response;
    }
}
