package com.ssts.ssts.global.auth.controller;

import com.ssts.ssts.domain.badges.service.BadgeService;
import com.ssts.ssts.domain.member.dto.MemberSignUpAddInfoDto;
import com.ssts.ssts.global.auth.dto.AccessTokenResponse;
import com.ssts.ssts.global.auth.dto.AuthenticationTokenResponse;
import com.ssts.ssts.global.auth.dto.LoginResponse;
import com.ssts.ssts.global.auth.service.OAuthService;
import com.ssts.ssts.global.auth.service.TokenService;
import com.ssts.ssts.global.auth.utils.AuthConsts;
import com.ssts.ssts.global.utils.MultipleResponseDto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class LoginSignupController {

    private final OAuthService oauthService;
    private final TokenService tokenService;
    private final BadgeService badgeService;

    /*
     * 로그인
     * 권한 : AUTH, ADMIN
     * */
    @GetMapping("/login/auth")
    public ApiResponse<LoginResponse> login(HttpServletResponse response) throws IOException {

        AuthenticationTokenResponse tokenResponse=oauthService.login();
        log.info("하늘/token response :\n"+
                tokenResponse.toString());

        response.setHeader("authorization", "Bearer " + tokenResponse.getAccessToken());
        response.setHeader("refresh", tokenResponse.getRefreshToken());

        tokenService.refreshTokenList(tokenResponse.getRefreshToken());

        return ApiResponse.ok(LoginResponse.of(tokenResponse.getNickName()));
    }


    /*
     * 회원가입
     * 권한 : GUEST, ADMIN
     * */
    //FIXME 핸드폰번호,이메일,닉네임이.. 다 넘어와야 하는데..어..
    //FIXME [보안문제] 이거 휴대폰 인증 API안쓰면 그냥 번호가 노출되서 나중에 무조건 고쳐야한다.
    //https://lasbe.tistory.com/132
    @PostMapping("/signup")
    public ApiResponse<LoginResponse> signUp(HttpServletResponse response, @RequestBody MemberSignUpAddInfoDto memberSignUpAddInfoDto){

        AuthenticationTokenResponse tokenResponse=oauthService.signup(memberSignUpAddInfoDto.getPhone(), memberSignUpAddInfoDto.getNickName());
        log.info("하늘/token response :\n"+
                tokenResponse.toString());

        response.setHeader("authorization", "Bearer " + tokenResponse.getAccessToken());
        response.setHeader("refresh", tokenResponse.getRefreshToken());

        tokenService.refreshTokenList(tokenResponse.getRefreshToken());

        badgeService.signupBadge(1L, tokenResponse.getNickName());

        return ApiResponse.ok(LoginResponse.of(tokenResponse.getNickName()));
    }



}
