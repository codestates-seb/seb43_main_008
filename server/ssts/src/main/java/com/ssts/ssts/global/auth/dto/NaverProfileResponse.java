package com.ssts.ssts.global.auth.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class NaverProfileResponse implements OAuthResponse {
    private String id;
    private String email;

    @JsonCreator
    public NaverProfileResponse(@JsonProperty("response") Map<String, String> data){
        this.id = data.get("id");
        this.email = data.get("email");
    }

}
