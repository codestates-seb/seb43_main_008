package com.ssts.ssts.global.auth.filter;

import com.ssts.ssts.global.auth.jwt.JwtTokenizer;
import com.ssts.ssts.global.auth.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//자격 증명이 필요한 리소스에 대한 request요청시 request header를 통해 전달받은 jwt를 서버측에서 검증해야 한다.
@RequiredArgsConstructor
@Slf4j
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("하늘/security : jwt verification filter -> 동작");
        Map<String, Object> claims = verifyJws(request); // 하위 메서드 호출 1. 서명 검증
        setAuthenticationToContext(claims);      // 하위 메서드 호출 2. securityContext에 Authentication 객체 저장

        filterChain.doFilter(request, response); // 다음 필터로 동작 지시
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");
        log.info("하늘/security : jwt verification filter -> 사전 검사 ");
        return authorization == null || !authorization.startsWith("Bearer");
    }

    //JWT 검증 1. 서명 검증
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        // jws 토큰 가져오기
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        // key 생성하기

        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();
        // 토큰에서 클레임즈 파싱 -> (검증)
        // 이 과정에서 subject에 넣어놨던 email을 claims Map 에 key("sub"), value(email)로 들어가게 된다.

        return claims;
    }

    //Authentication 객체를 SecurityContext에 저장 2. Authentication 저장 -> SecurityUtil에서 접근할 수 있음.
    private void setAuthenticationToContext(Map<String, Object> claims) {

        String principal = (String) claims.get("sub");
        log.info("하늘/security set authentication to context\n"+
                "principal="+principal);

        Map<String, Object> credentials=new HashMap<>();
        credentials.put("id",claims.get("id"));
        //GrantedAuthority String -> string
        List<String> fixRoles=authorityUtils.grantedAuthorityStringToRoleString((List)claims.get("roles"));
        //string -> GrantedAuthority
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities(fixRoles);

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContext에 Authentication객체 저장한다.

    }
}
