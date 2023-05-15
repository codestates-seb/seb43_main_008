package com.ssts.ssts.auth.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleProfileResponse implements OAuthResponse {
    private String id;
    private String email;

    @JsonCreator
    public GoogleProfileResponse(
            @JsonProperty("id") String id,
            @JsonProperty("email") String email
    ) {
        this.id = id;
        this.email = email;
    }
}
