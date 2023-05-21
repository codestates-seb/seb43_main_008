package com.ssts.ssts.global.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 허용할 오리진(도메인) 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메소드 설정
                .exposedHeaders("Authorization", "Refresh")
                .allowCredentials(true) // 인증 정보 허용 여부 설정
                .maxAge(3600); // preflight 요청 결과 캐싱 시간 설정
    }
}