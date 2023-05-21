package com.ssts.ssts.global.auth.api;

import com.ssts.ssts.global.auth.dto.GoogleProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "google", url="https://www.googleapis.com")
public interface GoogleApi {
    @GetMapping(value = "/oauth2/v2/userinfo")
    GoogleProfileResponse getGoogleUser(@RequestHeader("Authorization") String accessToken);
}
