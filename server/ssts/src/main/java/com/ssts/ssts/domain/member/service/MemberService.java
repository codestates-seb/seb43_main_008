package com.ssts.ssts.domain.member.service;

import com.ssts.ssts.domain.member.dto.MemberSignUpPostDto;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    //세큐리티 추가시 동작가능
    //private final PasswordEncoder passwordEncoder;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member saveMember(MemberSignUpPostDto memberSignUpPostDto) {
        //ouath 로만 로그인 구현하면.. password 필요없는거 아냐..? 나중에 구현 후 생각해보기.
        Member member=Member.of(memberSignUpPostDto.getNickName(),
                memberSignUpPostDto.getEmail(),
                memberSignUpPostDto.getPassword());

        verifyExistsEmail(member.getEmail());
        // 중복되지 않는 이메일이라면 그때 비밀번호 암호화하기
        //member.setPassword(passwordEncoder.encode(member.getPassword());

        //세큐리티 추가시 동작가능
        //List<String> roles = authorityUtils.createRoles(member.getEmail());
        //임시 동작 : admin 권한은 이메일에 @ssts.com이 포함되어있으면 관리자!
        List<String> roles;
        if(isAdmin(member.getEmail())){
            roles=List.of("ADMIN","USER");
        }else{
            roles=List.of("USER");
        }
        member.setRoles(roles);

        Member savedMember=memberRepository.save(member);

        return savedMember;
    }

    public void verifyExistsEmail(String email) {
        //Member member = memberRepository.findByEmail(email).orElseThrow(NoSuchElementException::new);
        Optional<Member> member=memberRepository.findByEmail(email);
        if(member.isPresent()){
            throw new BusinessLogicException(ExceptionCode.EMAIL_DUPLICATE);
        }
    }

    private boolean isAdmin(String email){
        if(email.contains("@ssts.com")){
            return true;
        }else{
            return false;
        }
    }
}
