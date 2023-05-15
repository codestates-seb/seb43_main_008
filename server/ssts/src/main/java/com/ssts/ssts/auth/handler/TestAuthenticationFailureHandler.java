package com.ssts.ssts.auth.handler;

import com.google.gson.Gson;
import com.nimbusds.oauth2.sdk.ErrorResponse;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // 인증 실패 시, 에러 로그를 기록하거나 error response를 전송할 수 있다.
        log.error("하늘/security : test authentication failure", exception.getMessage());

        throw new BusinessLogicException(ExceptionCode.SECURITY_TEST_LOGIN_NO_MEMBER);// (2)
    }

}
