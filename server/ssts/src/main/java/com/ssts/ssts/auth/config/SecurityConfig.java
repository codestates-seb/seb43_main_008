package com.ssts.ssts.auth.config;

import com.ssts.ssts.auth.filter.JwtTestAuthenticationFilter;
import com.ssts.ssts.auth.filter.JwtVerificationFilter;
import com.ssts.ssts.auth.handler.OAuth2MemberFailureHandler;
import com.ssts.ssts.auth.handler.OAuth2MemberSuccessHandler;
import com.ssts.ssts.auth.handler.TestAuthenticationFailureHandler;
import com.ssts.ssts.auth.handler.TestAuthenticationSuccessHandler;
import com.ssts.ssts.auth.jwt.JwtTokenizer;
import com.ssts.ssts.auth.service.CustomOAuth2UserService;
import com.ssts.ssts.auth.utils.CustomAuthorityUtils;
import com.ssts.ssts.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer())
                .and()
//                .addFilterBefore(new JwtTestAuthenticationFilter(jwtTokenizer, authorityUtils,memberService), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JwtVerificationFilter(jwtTokenizer, authorityUtils), JwtTestAuthenticationFilter.class)
                .authorizeRequests(authorize -> authorize
                        .antMatchers(HttpMethod.PATCH,"/**/members/edit/**").hasRole("USER")
                        .antMatchers("/login/**").permitAll()
                        .antMatchers("/test/login").permitAll()
                        .antMatchers("/test/**").permitAll()
                        .antMatchers("/test/signup").permitAll()
                        //.anyRequest().authenticated()) //FIXME 인증 끌때 여기 주석처리하세요
                        .anyRequest().permitAll());
//                .oauth2Login()
//                .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer))
//                .failureHandler(new OAuth2MemberFailureHandler())
//                .userInfoEndpoint().userService(new CustomOAuth2UserService(memberService, authorityUtils));


        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {

            JwtTestAuthenticationFilter jwtAuthenticationFilter = new JwtTestAuthenticationFilter(jwtTokenizer, authorityUtils, memberService);
            jwtAuthenticationFilter.setFilterProcessesUrl("/test/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new TestAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new TestAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtTestAuthenticationFilter.class);

        }
    }

}
