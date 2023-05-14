package com.ssts.ssts.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/login")
@Slf4j
public class TestOAuth2Controller {

    private static final String GOOGLE_AUTHORIZATION_REQUEST_BASE_URI = "https://accounts.google.com/o/oauth2/v2/auth";

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @GetMapping("/test/google")
    public void RedirectGoogle(HttpServletResponse response) throws IOException {
        String redirectUri = "http://localhost:8080/login/oauth2/code/google";

        String authUrl = GOOGLE_AUTHORIZATION_REQUEST_BASE_URI
                + "?client_id=" + googleClientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=code"
                + "&scope=email profile";

        response.sendRedirect(authUrl);
    }
}
