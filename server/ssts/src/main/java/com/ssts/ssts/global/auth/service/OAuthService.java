package com.ssts.ssts.global.auth.service;

import com.ssts.ssts.global.auth.dto.GoogleProfileResponse;
import com.ssts.ssts.global.auth.dto.KakaoProfileResponse;
import com.ssts.ssts.global.auth.dto.OAuthTokenResponse;
import com.ssts.ssts.global.auth.jwt.JwtTokenizer;
import com.ssts.ssts.global.auth.utils.CustomAuthorityUtils;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
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
    private final MemberService memberService;
    private final CustomAuthorityUtils authorityUtils;
    private final JwtTokenizer jwtTokenizer;

    public OAuthTokenResponse accessResources(String code, String socialType) {

        log.info("하늘/oauth service : access resources()" +
                        "\nsocialType="+socialType);
        String email;

        if("google".equals(socialType)){
            GoogleProfileResponse googleProfileResponse = googleInfraService.getGoogleAccount(googleInfraService.getAccessToken(code));
            email = googleProfileResponse.getEmail();
        } else if ("kakao".equals(socialType)) {
            KakaoProfileResponse kakaoProfileResponse = kakaoInfraService.getKakaoAccount(kakaoInfraService.getAccessToken(code));
            email = kakaoProfileResponse.getEmail();
        } else{
            email = "yunide073@gmail.com";
        }

        return resourceAccessTokenResponse(email);
    }

    public OAuthTokenResponse login() {

        Member member=memberService.findMemberByToken();
        log.info("하늘/oauth service : login"+
                "\nid="+member.getId()+
                "\nemail="+member.getEmail()+
                "\nauthority="+SecurityUtil.getAuthorities().get(0)+
                "\nroles="+member.getRoles().get(0));

        return authorizationTokenResponse(member);
    }

    public OAuthTokenResponse signup(String phone, String nickName){

        Member member=memberService.signUpMember(phone, nickName);
        log.info("하늘/oauth service : signup"+
                "\nid="+member.getId()+
                "\nemail="+member.getEmail()+
                "\nauthority="+SecurityUtil.getAuthorities().get(0)+
                "\nroles="+member.getRoles().get(0));

        return authorizationTokenResponse(member);
    }

    public OAuthTokenResponse authorizationTokenResponse(Member member){
        List<String> authorityList = authorityUtils.createAuthorities(member.getRoles())
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String accessToken=delegateAccessToken(member.getId(), member.getEmail(), authorityList);
        String refreshToken=delegateRefreshToken(member.getEmail());

        return OAuthTokenResponse.of(accessToken,refreshToken);
    }

    public OAuthTokenResponse resourceAccessTokenResponse(String email) {

        Optional<Member> member= memberService.findMemberByEmail(email);
        List<String> authorityList;
        Long id = null;

        if(member.isPresent()){
            id = member.get().getId();
            authorityList = authorityUtils.createAuthorities(List.of("AUTH"))
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }
        else{
            authorityList = authorityUtils.createAuthorities(List.of("GUEST"))
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }

        log.info("하늘/oauth service : access resources" +
                "\nid=" + id +
                "\nemail=" + email+
                "\nauthority="+ authorityList.get(0));

        //FIXME id가 필요한 것인가? 고민하기
        String accessToken = delegateAccessToken(id, email, authorityList);
        log.info("하늘/oauth service : access resources" +
                "\n발급한 access token Bearer {}", accessToken);

        return OAuthTokenResponse.of(accessToken, email, member.isPresent());
    }

    //FIXME
    private String delegateAccessToken(Long id, String email, List<String> authorities) {

        // 1.subject = email (principal)
        String subject = email;

        // 2.claim = id, roles
        // FIXME 나중에 스트림으로 처리하기
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authorities);
        claims.put("id", id);

        // claims 구성 확인
        String claimsStr="";
        Iterator<String> keys=claims.keySet().iterator();
        while(keys.hasNext()){
            String key=keys.next();
            if(claims.get(key)!=null)
                claimsStr+=key+"="+claims.get(key).toString()+"\n";
        }
        log.info("하늘/oauth jwt : [claims]\n"+ claimsStr);

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(String email) {

        String subject = email;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
