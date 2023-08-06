package com.ssts.ssts.global.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssts.ssts.global.auth.dto.TestLoginDto;
import com.ssts.ssts.global.auth.jwt.JwtCreator;
import com.ssts.ssts.global.auth.jwt.JwtTokenizer;
import com.ssts.ssts.global.auth.utils.CustomAuthorityUtils;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.global.auth.utils.SocialType;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class JwtTestAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //private final JwtTokenizer jwtTokenizer;
    private final JwtCreator jwtCreator;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;


    // 인증을 시도하는 로직 -> Test용이라서 email만 DB에 등록되어 있어도 인증이 성공한다.
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        log.info("하늘 security : jwt test authentication filter");
        // 클라이언트 -> 서버 역직렬화(json->object)
        ObjectMapper objectMapper = new ObjectMapper();
        TestLoginDto testLoginDto = objectMapper.readValue(request.getInputStream(), TestLoginDto.class);

        // ServletInputStream -> TestLoginDto(역직렬화)
        log.info("하늘 security : 입력 email="+testLoginDto.getEmail());
        Optional<Member> member = memberService.findOptionalMemberByEmail(testLoginDto.getEmail());

        if(member.isPresent()){
            //회원일 경우

            Map<String, Object> credentials=new HashMap<>();
            credentials.put("id",member.get().getId());
            credentials.put("socialType",member.get().getSocialType().toString());

            List<GrantedAuthority> authorityList=authorityUtils.dbRolesToAuthorities(member.get().getRoles());

            UsernamePasswordAuthenticationToken UserAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(member.get().getEmail(),credentials, authorityList);

            return UserAuthenticationToken;

        }else{
            // 비회원일 경우
            List<GrantedAuthority> authorityList=authorityUtils.dbRolesToAuthorities(List.of("GUEST"));

            UsernamePasswordAuthenticationToken GuestAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(testLoginDto.getEmail(),null, authorityList);

            return GuestAuthenticationToken;
        }
    }

    // 인증에 성공할 경우 호출된다.
    // AbstractAuthenticationProcessingFilter에서 호출한다고 뜬다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws ServletException, IOException {

        log.info("하늘/security : jwt test authentication filter -> success ");

        String email = (String) authResult.getPrincipal();

        List<String> authorities = authResult.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        log.info("email=" + email +
                "\nauthorities=" + authorities.get(0));

        if(authResult.getCredentials()!=null){

            Map<String, Object> credentials =  (Map<String, Object>)authResult.getCredentials();

            String socialType=(String)credentials.get("socialType");
            long id =(long)credentials.get("id");

            String accessToken = jwtCreator.delegateUserAccessToken(id, email, socialType, authorities);
            String refreshToken = jwtCreator.delegateUserRefreshToken(email, socialType);

            response.setHeader("authorization", "Bearer " + accessToken);
            response.setHeader("refresh", refreshToken);

            this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);

        }else{

           /* String accessToken = jwtCreator.delegateLoginAndSignupAccessToken(email, socialType, authorities);

            response.setHeader("authorization", "Bearer " + accessToken);

            this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);*/


            this.getFailureHandler().onAuthenticationFailure(request, response, null);
        }

    }

}
