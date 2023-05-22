package com.ssts.ssts.global.auth.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CustomAuthorityUtils {

    //[입력] USER (String)
    //[출력] ROLE_USER (GrantedAuthority)
    public List<GrantedAuthority> dbRolesToAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return authorities;
    }

    //[입력] ROLE_USER (String)
    //[출력] ROLE_USER (GrantedAuthority)
    public List<GrantedAuthority> stringRolesToAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return authorities;
    }

}
