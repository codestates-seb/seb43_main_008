package com.ssts.ssts.global.auth.api;

import com.ssts.ssts.global.auth.dto.KakaoProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakao", url="https://kapi.kakao.com")
public interface KakaoApi {
    @GetMapping(value = "/v2/user/me", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    KakaoProfileResponse getKakaoUser(@RequestHeader(name = "Authorization") String accessToken);
}
