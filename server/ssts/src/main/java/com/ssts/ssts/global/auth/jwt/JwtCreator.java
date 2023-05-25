package com.ssts.ssts.global.auth.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtCreator {

    private final JwtTokenizer jwtTokenizer;

    public String delegateLoginAndSignupAccessToken(Long id, String email, String socialType, List<String> authorities){

        String subject = email;

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("roles", authorities);
        claims.put("socialType", socialType);

        String claimsStr="";
        Iterator<String> keys=claims.keySet().iterator();
        while(keys.hasNext()){
            String key=keys.next();
            if(claims.get(key)!=null)
                claimsStr+="["+key+"]="+claims.get(key).toString()+"\n";
        }
        log.info("하늘/jwt(username password) : claims=\n"+ claimsStr);

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    public String delegateUserAccessToken(long id, String email, String socialType, List<String> authorities) {

        String subject = email;

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("roles", authorities);
        claims.put("socialType", socialType);

        // claims 구성 확인
        // FIXME 나중에 스트림으로 처리하기
        String claimsStr="";
        Iterator<String> keys=claims.keySet().iterator();
        while(keys.hasNext()){
            String key=keys.next();
            claimsStr+="["+key+"]="+claims.get(key).toString()+"\n";
        }
        log.info("하늘/jwt(username password) : claims=\n"+ claimsStr);

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    public String delegateUserRefreshToken(String email, String socialType) {

        String subject = email;

        Map<String, Object> claims = new HashMap<>();
        claims.put("socialType", socialType);

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String refreshToken = jwtTokenizer.generateRefreshToken(claims, subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
