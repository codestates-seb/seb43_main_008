package com.ssts.ssts.global.auth.service;

import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.global.auth.dto.AuthenticationTokenResponse;
import com.ssts.ssts.global.auth.jwt.JwtTokenizer;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.security.SecurityUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenizer jwtTokenizer;
    private final MemberService memberService;
    private final OAuthService oauthService;
    //private final RedisTemplate redisTemplate;

    private final Map<String, String> tokens=new HashMap<>();

    public void checkAccessToken(String authorization){

        String jws = authorization.replace("Bearer ", "");

        if(authorization.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.JWT_NO_TOKEN);
        }else if(validationToken(jws)){ //블랙리스트에 있으면 거절하기
            throw new BusinessLogicException(ExceptionCode.JWT_NOT_VALID);
        }

        //String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        jwtTokenizer.verifySignature(jws);
    }

    public AuthenticationTokenResponse checkRefreshToken(String refresh){

        if(refresh.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.JWT_NO_TOKEN);
        }

        //String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        jwtTokenizer.verifySignature(refresh);

        String email = jwtTokenizer.getSubjectFromToken(refresh);
        log.info("하늘/token service\n"+
                "email="+email);

        if(!validationToken("RT:"+email)) //리프레시 토큰이 없으면 거절하기
            throw new BusinessLogicException(ExceptionCode.JWT_NOT_VALID);

        Optional<Member> member = memberService.findMemberByEmail(email);
        if (member.isPresent()) {
            return oauthService.authorizationTokenResponse(member.get());
        }else{
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
    }

    //리프레시 토큰은 RT:email 형식으로 들어간다.
    //액세스 토큰은 그대로 들어간다. (블랙 리스트화)
    public void blackList(String access){
        String email= jwtTokenizer.getSubjectFromToken(access);

        String refresh=tokens.get("RT:"+email);
        if(refresh==null)
            throw new BusinessLogicException(ExceptionCode.JWT_NO_TOKEN);
        else
            tokens.remove("RT:"+email);

        tokens.put(access, "logout");
        log.info("하늘/blackList\n" +
                tokens.get(access));
    }

    public void refreshTokenList(String refresh){

        String email= jwtTokenizer.getSubjectFromToken(refresh);
        tokens.put("RT:"+email, refresh);
    }

    public boolean validationToken(String key){

        if(tokens.containsKey(key)){
            return true;
        }
        return false;
    }
}
