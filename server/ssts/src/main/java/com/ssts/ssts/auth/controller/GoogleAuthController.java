package com.ssts.ssts.auth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/members/login/google")
public class GoogleAuthController {


    @GetMapping
    public void redirect(HttpServletResponse response) throws IOException {
        System.out.println("하늘: controller 실행");
        response.sendRedirect("http://localhost:8080/login/oauth2/code/google");
    }

}
