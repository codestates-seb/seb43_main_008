package com.ssts.ssts.utils.security;

import com.ssts.ssts.auth.utils.CustomOAuth2User;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {


    public static Long getMemberId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication == null || authentication.getCredentials()==null) {
            throw new BusinessLogicException(ExceptionCode.SECURITY_NO_CREDENTIALS);
        }

        Map<String, Object> credentials =  (Map<String, Object>)authentication.getCredentials();
        Integer integerId=(Integer)credentials.get("id");
        //Long id=new Long(credentials.get("id").longValue());
        long longId = new Long(integerId.longValue());
        return longId;
    }
}

