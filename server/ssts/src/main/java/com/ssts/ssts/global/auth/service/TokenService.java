package com.ssts.ssts.global.auth.service;

import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.global.auth.dto.AuthenticationTokenResponse;
import com.ssts.ssts.global.auth.jwt.JwtTokenizer;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenizer jwtTokenizer;
    private final MemberService memberService;
    private final OAuthService oauthService;

    public void checkAccessToken(String authorization){

        if(authorization.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.JWT_NO_TOKEN);
        }

        String jws = authorization.replace("Bearer ", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        jwtTokenizer.verifySignature(jws, base64EncodedSecretKey);
    }

    public AuthenticationTokenResponse checkRefreshToken(String refresh){

        if(refresh.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.JWT_NO_TOKEN);
        }

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        jwtTokenizer.verifySignature(refresh, base64EncodedSecretKey);

        String email = jwtTokenizer.getSubjectFromRefreshToken(refresh, base64EncodedSecretKey);
        log.info("하늘/token service\n"+
                "email="+email);

        Optional<Member> member = memberService.findMemberByEmail(email);
        if (member.isPresent()) {
            return oauthService.authorizationTokenResponse(member.get());
        }else{
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
    }


}
