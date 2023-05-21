package com.ssts.ssts.global.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        log.error("하늘/security : test authentication success");
        if(authentication.getAuthorities().toString().equals("[ROLE_GUEST]")){

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("test 로그인 성공 -> 게스트 토큰 발급");
        }else{
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("test 로그인 성공 -> 유저 토큰 발급");

        }
    }
}
