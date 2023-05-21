package com.ssts.ssts.global.utils.security;

import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {


    public static Long getMemberId() {

        Map<String, Object> credentials = (Map<String, Object>)roadAuthentication().getCredentials();
        Integer integerId=(Integer)credentials.get("id");
        //Long id=new Long(credentials.get("id").longValue());
        long longId = new Long(integerId.longValue());
        return longId;
    }

    public static String getMemberEmail(){

        String email=(String)roadAuthentication().getPrincipal();

        return email;

    }

    public static List<String> getAuthorities(){

        List<GrantedAuthority> authorities = (List<GrantedAuthority>)roadAuthentication().getAuthorities();

        return authorities.stream().map(authority->authority.getAuthority()).collect(Collectors.toList());

    }

    public static Authentication roadAuthentication(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getCredentials()==null) {
            throw new BusinessLogicException(ExceptionCode.SECURITY_NO_CREDENTIALS);
        }

        return authentication;
    }
}

