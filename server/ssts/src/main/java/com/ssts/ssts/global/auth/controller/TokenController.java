package com.ssts.ssts.global.auth.controller;

import com.ssts.ssts.global.auth.dto.AuthenticationTokenResponse;
import com.ssts.ssts.global.auth.service.TokenService;
import com.ssts.ssts.global.utils.MultipleResponseDto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class TokenController {

    private final TokenService tokenService;

    @GetMapping("/token/check")
    public ApiResponse checkAccessTokenExpiration(@RequestHeader(value = "Authorization", defaultValue = "") String authorization){
        log.info("하늘/token check");

        tokenService.checkAccessToken(authorization);

        return ApiResponse.ok("정상 토큰이예요.");
    }

    @GetMapping("/token/reissue")
    public void reissueAccessToken(HttpServletResponse response,
                                   @RequestHeader(value="Authorization", defaultValue = "") String refresh){
        log.info("하늘/reissue token");

        AuthenticationTokenResponse tokenResponse=tokenService.checkRefreshToken(refresh);

        response.setHeader("authorization", "Bearer " + tokenResponse.getAccessToken());

        response.setStatus(HttpServletResponse.SC_OK);

    }
}


