package com.ssts.ssts.global.auth.service;

import com.ssts.ssts.global.auth.dto.*;
import com.ssts.ssts.global.auth.jwt.JwtCreator;
import com.ssts.ssts.global.auth.utils.CustomAuthorityUtils;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.global.auth.utils.SocialType;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

    private final GoogleInfraService googleInfraService;
    private final KakaoInfraService kakaoInfraService;
    private final NaverInfraService naverInfraService;
    private final MemberService memberService;
    private final CustomAuthorityUtils authorityUtils;
    private final JwtCreator jwtCreator;

    public AccessTokenResponse accessResources(String code, String type) {

        log.debug("하늘/oauth service : access resources()" +
                        "\nsocialType="+type);
        String email;
        SocialType socialType=SocialType.stringToSocialType(type);

        if(socialType==SocialType.GOOGLE){
            GoogleProfileResponse googleProfileResponse = googleInfraService.getGoogleAccount(googleInfraService.getAccessToken(code));
            email = googleProfileResponse.getEmail();

        } else if (socialType==SocialType.KAKAO) {
            KakaoProfileResponse kakaoProfileResponse = kakaoInfraService.getKakaoAccount(kakaoInfraService.getAccessToken(code));
            email = kakaoProfileResponse.getEmail();

        } else if (socialType==SocialType.NAVER){
            NaverProfileResponse naverProfileResponse = naverInfraService.getNaverAccount(naverInfraService.getAccessToken(code));
            email = naverProfileResponse.getEmail();

        } else{
            throw new BusinessLogicException(ExceptionCode.AUTH_NOT_SUPPORTED_SOCIAL_TYPE);
        }

        return resourceAccessTokenResponse(email, socialType);
    }

    public AuthenticationTokenResponse login() {

        Member member=memberService.getMemberByToken();

        log.debug("하늘/oauth service : login"+
                "\nid="+member.getId()+
                "\nemail="+member.getEmail()+
                "\nauthority="+SecurityUtil.getAuthorities().get(0)+
                "\nroles="+member.getRoles().get(0));

        return authorizationTokenResponse(member);
    }

    public AuthenticationTokenResponse signup(String phone, String nickName){

        String socialStr=SecurityUtil.getSocialType();
        String email=SecurityUtil.getMemberEmail();
        Member member=memberService.saveMember(email, phone, nickName, SocialType.stringToSocialType(socialStr));

        log.info("하늘/oauth service : signup"+
                "\nid="+member.getId()+
                "\nemail="+member.getEmail()+
                "\nsocialType="+socialStr+
                "\n[저장 전]authority="+SecurityUtil.getAuthorities().get(0)+
                "\n[저장 후]roles="+member.getRoles().get(0));

        return authorizationTokenResponse(member);
    }


    public AuthenticationTokenResponse authorizationTokenResponse(Member member){
        List<String> authorityList = authorityUtils.dbRolesToAuthorities(member.getRoles())
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String accessToken=jwtCreator.delegateUserAccessToken(member.getId(), member.getEmail(), member.getSocialType().toString(), authorityList);
        String refreshToken=jwtCreator.delegateUserRefreshToken(member.getEmail(), member.getSocialType().toString());

        return AuthenticationTokenResponse.of(accessToken,refreshToken,member.getNickName());
    }

    public AccessTokenResponse resourceAccessTokenResponse(String email, SocialType socialType) {

        Optional<Member> member= memberService.findOptionalMemberByEmailAndSocialType(email, socialType);
        List<String> authorityList;
        Long id = null;

        if(member.isPresent()){
            id = member.get().getId();
            authorityList = authorityUtils.dbRolesToAuthorities(List.of("AUTH"))
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }
        else{
            authorityList = authorityUtils.dbRolesToAuthorities(List.of("GUEST"))
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }

        log.info("하늘/oauth service : access resources" +
                "\nid=" + id +
                "\nemail=" + email+
                "\nauthority="+ authorityList.get(0));

        String accessToken = jwtCreator.delegateLoginAndSignupAccessToken(id, email, socialType.toString(), authorityList);

        return AccessTokenResponse.of(accessToken, email, member.isPresent());
    }

}
