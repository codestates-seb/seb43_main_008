package com.ssts.ssts.auth.service;

import com.ssts.ssts.auth.utils.CustomAuthorityUtils;
import com.ssts.ssts.auth.utils.CustomOAuth2User;
import com.ssts.ssts.auth.utils.SocialType;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberService memberService;
    private final CustomAuthorityUtils authorityUtils;

    private static final String NAVER = "naver";
    private static final String KAKAO = "kakao";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("하늘/security : CustomOAuth2UserService.loadUser() 실행 - OAuth2 로그인 요청 진입");
        //결과적으로, OAuth2User는 OAuth 서비스에서 가져온 유저 정보를 담고 있는 유저
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        // DefaultOAuth2UserService가 OAuth2UserRequest를 받고, 재가공해서 OAuth2User 객체로 만든다.

        /**
         * userRequest에서 registrationId 추출 후 registrationId으로 SocialType 저장
         * http://localhost:8080/oauth2/authorization/kakao에서 kakao가 registrationId
         * userNameAttributeName은 이후에 nameAttributeKey로 설정된다.
         */
        // OAuth2UserRequest객체 -> ClientRegistration객체(OAuth2 클라이언트 정보) -> (String)registrationId 필드값 가져오기.
        String providerId = userRequest.getClientRegistration().getRegistrationId();
        // 구글인지? 카카오인지? 네이버인지? OAuth 이름 확인
        log.info("하늘/security : provider="+providerId);

        SocialType socialType = getSocialType(providerId);
        //provider id -> 각 소셜마다 response 타입이 따로 있는데, 각 OAUTH response를 찾아봐야한다.

        // OAuth2UserRequest객체 -> ClientRegistration객체(OAuth2 클라이언트 정보)
        // -> ProviderDetails객체 -> UserInfoEndpoint객체 -> (String)userNameAttributeName 필드값 가져오기
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); // OAuth2 로그인 시 키(PK)가 되는 값 ???????????
        log.info("하늘/security : userNameAttributeName="+userNameAttributeName);

        Map<String, Object> attributes = oAuth2User.getAttributes(); // 소셜 로그인에서 API가 제공하는 userInfo의 Json 값(유저 정보들) >> response
        //////////////* 속성값 보는 작업 / 로직 무관 *//////////////////////////
        String attributesStr="";
        Iterator<String> keys=attributes.keySet().iterator();
        while(keys.hasNext()){
            String key=keys.next();
            attributesStr+="["+key+"]="+attributes.get(key).toString()+"\n";
        }
        log.info("하늘/security : attributes=\n"+ attributesStr);
        /* 구글 정보
            attributes=
            [sub]=114237756963603122548
            [name]=neul ha
            [given_name]=neul
            [family_name]=ha
            [picture]=https://lh3.googleusercontent.com/a/AGNmyxZhQDP6NP7dtNMc6dCTTW-p5LNko31zD_aXPtXgGA=s96-c
            [email]=yunide073@gmail.com
            [email_verified]=true
            [locale]=ko
         */
        /* 카카오 정보
            [id]=2784542083
            [connected_at]=2023-05-10T18:59:47Z
            [kakao_account]={has_email=true, email_needs_agreement=false, is_email_valid=true, is_email_verified=true, email=yunide2@naver.com}
         */
        //////////////////////////////////////////////////////////

        String email=getEmailBySocialType(socialType, attributes);
        // 멤버 만든다아...
        Optional<Member> member = memberService.findMemberByEmail(email); // getUser() 메소드로 User 객체 생성 후 반환


        if(member.isPresent()){
            // DefaultOAuth2User를 구현한 CustomOAuth2User 객체를 생성해서 반환
            return new CustomOAuth2User(
                    authorityUtils.createAuthorities(member.get().getRoles()),
                    attributes,
                    userNameAttributeName,
                    member.get().getId(),
                    email
            );

        }
        return new CustomOAuth2User(
                authorityUtils.createAuthorities(List.of("GUEST")),
                attributes,
                userNameAttributeName,
                email
        );
    }

    private SocialType getSocialType(String registrationId) {
        if(NAVER.equals(registrationId)) {
            return SocialType.NAVER;
        }
        if(KAKAO.equals(registrationId)) {
            return SocialType.KAKAO;
        }
        return SocialType.GOOGLE;
    }

    private String getEmailBySocialType(SocialType socialType,Map<String, Object> attributes ) {
        if(socialType==SocialType.KAKAO){
            Map<String, Object> map=(Map<String, Object>) attributes.get("kakao_account");
            String str=(String) map.get("email");
            log.info("하늘/security kakao="+str);
            return str;
        }
        if(socialType==SocialType.NAVER){
            return "sample@ssts.com";
        }
        return (String) attributes.get("email");
    }

    /**
     * SocialType과 attributes에 들어있는 소셜 로그인의 식별값 id를 통해 회원을 찾아 반환하는 메소드
     * 만약 찾은 회원이 있다면, 그대로 반환하고 없다면 saveUser()를 호출하여 회원을 저장한다.
     */
//    private User getUser(OAuthAttributes attributes, SocialType socialType) {
//        User findUser = userRepository.findBySocialTypeAndSocialId(socialType,
//                attributes.getOauth2UserInfo().getId()).orElse(null);
//
//        if(findUser == null) {
//            return saveUser(attributes, socialType);
//        }
//        return findUser;
//    }
//
//    /**
//     * OAuthAttributes의 toEntity() 메소드를 통해 빌더로 User 객체 생성 후 반환
//     * 생성된 User 객체를 DB에 저장 : socialType, socialId, email, role 값만 있는 상태
//     */
//    private User saveUser(OAuthAttributes attributes, SocialType socialType) {
//        User createdUser = attributes.toEntity(socialType, attributes.getOauth2UserInfo());
//        return userRepository.save(createdUser);
//    }
}
