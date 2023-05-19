package com.ssts.ssts.global.utils.security;

import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {


    public static Long getMemberId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getCredentials()==null) {
            throw new BusinessLogicException(ExceptionCode.SECURITY_NO_CREDENTIALS);
        }

        //FIXME 여기도...으악...
        Map<String, Object> credentials =  (Map<String, Object>)authentication.getCredentials();

        //FIXME 아 사실 너무 마음에 안들어.....
        Integer integerId=(Integer)credentials.get("id"); // V = Object값이 출력된다.
        //Long id=new Long(credentials.get("id").longValue());
        Long longId = new Long(integerId.longValue());
        if(longId == null){
            throw new BusinessLogicException(ExceptionCode.IS_NULL);
        }
        return longId;
    }

    public static String getMemberEmail(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getCredentials()==null) {
            throw new BusinessLogicException(ExceptionCode.SECURITY_NO_CREDENTIALS);
        }

        return authentication.getPrincipal().toString();

    }
}

