package com.ssts.ssts.global.auth.controller;

import com.ssts.ssts.domain.member.dto.MemberSignUpAddInfoDto;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.global.auth.dto.OAuthTokenResponse;
import com.ssts.ssts.global.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class LoginSignupController {

    private final OAuthService oauthService;

    @GetMapping("/login/auth")
    public void login(HttpServletResponse response) throws IOException {
        OAuthTokenResponse tokenResponse=oauthService.login();
        log.info("하늘/token response :\n"+
                tokenResponse.toString());

        response.setHeader("Authorization", "Bearer " + tokenResponse.getAccessToken());
        response.setHeader("Refresh", tokenResponse.getRefreshToken());
    }


    /*
     * 회원가입
     * 권한 : USER, ADMIN
     * */
    //FIXME 핸드폰번호,이메일,닉네임이.. 다 넘어와야 하는데..어..
    //FIXME [보안문제] 이거 휴대폰 인증 API안쓰면 그냥 번호가 노출되서 나중에 무조건 고쳐야한다.
    //https://lasbe.tistory.com/132
    @PostMapping("/signup")
    public void signUp(HttpServletResponse response, @RequestBody MemberSignUpAddInfoDto memberSignUpAddInfoDto){
        OAuthTokenResponse tokenResponse=oauthService.signup(memberSignUpAddInfoDto.getPhone(), memberSignUpAddInfoDto.getNickName());
        log.info("하늘/token response :\n"+
                tokenResponse.toString());

        response.setHeader("Authorization", "Bearer " + tokenResponse.getAccessToken());
        response.setHeader("Refresh", tokenResponse.getRefreshToken());

    }

}
