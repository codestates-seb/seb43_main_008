package com.ssts.ssts.global.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class OAuth2MemberFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws OAuth2AuthenticationException, IOException, ServletException {
        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //response.getWriter().write("소셜 로그인 실패! 서버 로그를 확인해주세요.");
        log.info("하늘/security FailureHandler 진입, 에러메세지="+exception.getMessage());

    }
}
