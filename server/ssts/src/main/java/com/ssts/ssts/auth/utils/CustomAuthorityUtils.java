package com.ssts.ssts.auth.utils;

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

    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");
    private final List<String> GUEST_ROLES_STRING = List.of("GUEST");

    // DB에 저장된 Role을 기반으로 권한 정보 생성
    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return authorities;
    }


    public List<String> grantedAuthorityStringToRoleString(List<String> grantedAuthorityStringList){
        List<String> roles=new ArrayList<>();
        for (int i = 0; i < grantedAuthorityStringList.size(); i++) {
            String role = grantedAuthorityStringList.get(i).replaceFirst("\\[ROLE_(.*?)\\]", "$1").replaceFirst("ROLE_", "");;
            log.info("하늘/security : role="+role);
            roles.add(role);
        }
        return roles;
    }

    public List<String> createRoles(String email) {
        if (isAdmin(email)) {
            return ADMIN_ROLES_STRING;
        }
        return USER_ROLES_STRING;
    }

    private boolean isAdmin(String email){
        if(email.contains("@ssts.com")){
            return true;
        }else{
            return false;
        }
    }
}
