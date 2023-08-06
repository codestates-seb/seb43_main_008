package com.ssts.ssts.global.auth.filter;

import com.ssts.ssts.global.auth.jwt.JwtTokenizer;
import com.ssts.ssts.global.auth.service.TokenService;
import com.ssts.ssts.global.auth.utils.CustomAuthorityUtils;
import com.ssts.ssts.global.auth.utils.AuthConsts;
import com.ssts.ssts.global.exception.ExceptionCode;
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
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("하늘/security : jwt verification filter -> 동작");
        String jws = request.getHeader("Authorization").replace("Bearer ", "");

        if(tokenService.validationToken(jws)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ExceptionCode.JWT_NOT_VALID.getMessage());

        }else{
            Map<String, Object> claims = verifyJws(jws);
            setAuthenticationToContext(claims);

            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");
        String requestUri=request.getRequestURI();
        log.info("하늘/security : jwt verification filter -> 사전 검사 ");
        return authorization == null || !authorization.startsWith("Bearer")
                || AuthConsts.TOKEN_CHECK_URI.equals(requestUri)
                || AuthConsts.TOKEN_REISSUE_URI.equals(requestUri);
    }

    //JWT 검증 1. 서명 검증
    private Map<String, Object> verifyJws(String jws) {

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        return claims;
    }

    //Authentication 객체를 SecurityContext에 저장 2. Authentication 저장 -> SecurityUtil에서 접근할 수 있음.
    private void setAuthenticationToContext(Map<String, Object> claims) {

        String principal = (String) claims.get("sub");

        Map<String, Object> credentials=new HashMap<>();
        credentials.put("id",claims.get("id"));
        credentials.put("socialType", claims.get("socialType"));

        List<GrantedAuthority> authorities = authorityUtils.stringRolesToAuthorities((List<String>)claims.get("roles"));

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
