package com.ssts.ssts.global.auth.api;

import com.ssts.ssts.global.auth.dto.NaverProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "naver", url = "https://openapi.naver.com")
public interface NaverApi {

    @GetMapping(value = "/v1/nid/me", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    NaverProfileResponse getNaverUser(@RequestHeader("Authorization") String accessToken);

}