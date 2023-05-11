package com.ssts.ssts.utils.security;

import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import java.security.Principal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {
    public static Member getMember() {
        MemberPrincipal principal = (MemberPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //강제 형변환 안됨.


        if(ObjectUtils.isEmpty(principal.getMember())) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }

        return principal.getMember();
    }

    public static Long getMemberId() {
        return getMember().getId();
    }
}

