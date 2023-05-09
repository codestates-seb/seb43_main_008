package com.ssts.ssts.auth.filter;

import com.ssts.ssts.auth.jwt.JwtTokenizer;
import com.ssts.ssts.auth.utils.CustomAuthorityUtils;
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
import java.util.List;
import java.util.Map;

//자격 증명이 필요한 리소스에 대한  request요청시 request header를 통해 전달받은 jwt를 서버측에서 검증해야 한다.
@RequiredArgsConstructor
@Slf4j
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Map<String, Object> claims = verifyJws(request); // : 하위 메서드 호출
        setAuthenticationToContext(claims);      // : 하위 메서드 호출

        filterChain.doFilter(request, response);
        // 문제없이 서명 검증하고 > 세큐리티 컨텍스트에 권한을 저장한 후에는 다음 필터로 동작 지시한다.
    }

    // OncePerRequestFilter의 메서드
    //특정 조건을 만족하는지 확인하고 true/false를 반환한다
    // -> authorization 헤더가 없거나, Bearer로 시작하지 않으면 다음 필터로 넘어간다
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");
        //아예 해당하는 속성이 헤더값에 없으면 에러 안나고 null값이 저장되는 건가? (질문)

        return authorization == null || !authorization.startsWith("Bearer");
    }

    //JWT를 검증하는데 사용된다.
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        // request 헤더에 포함된 인증부분에서 "Bearer"를 ""로 만든다(=제거한다)
        // Authorization생성할 때 Bearer문자열 붙인다. 왜?
        // bearer라는 키워드를 다른 단어로 치환하여 개발하는 경우가 있는데, 아주 좋지 않은 방식이고 약속된 틀을 잘 지키는 것이 중요
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();
        // jwt에서 claims를 파싱할 수 있다는 의미는 자연적으로 서명 검증에 성공했다는 의미이다.
        // 다른 메서드가 필요없다.

        //(질문) 내가 postman에서 헤더 Authorization의 입력값을 맘대로 바꿨는데 500에러만 뜬다.
        //내 생각엔 401에러가 뜰줄알았는데 이유가 뭐지?

        return claims;
    }

    //Authentication 객체를 SecurityContext에 저장하기 위한 private 메서드이다.
    private void setAuthenticationToContext(Map<String, Object> claims) {
        String username = (String) claims.get("username");   // (4-1) claims에서 username 얻고
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List)claims.get("roles"));  // (4-2) claims에서 얻은 roles을 이용해서 권한생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        // (4-3) username과 크레덴셜(민감한정보?), 권한으로 Authentication객체 생성하고
        SecurityContextHolder.getContext().setAuthentication(authentication); // (4-4) SecurityContext에 Authentication객체 저장한다.
        // 그후에 어떻게 사용하는데? 언제 사용하는데? 왜 저장하는데? >> 이유: 나중에 filter작업하려고
        // 이후 세션의 상태는? Spring Security의 세션 정책에 따라서 세션을 생성할 수도 있고 그렇지 않을 수 있다.

    }
}
