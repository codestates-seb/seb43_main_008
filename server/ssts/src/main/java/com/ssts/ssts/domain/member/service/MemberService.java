package com.ssts.ssts.domain.member.service;

import com.ssts.ssts.domain.member.dto.MemberEditInfoPatchDto;
import com.ssts.ssts.domain.member.dto.MemberEditInfoResponseDto;
import com.ssts.ssts.domain.member.dto.MemberMyPageResponseDto;
import com.ssts.ssts.domain.member.dto.MemberSignUpPostDto;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import com.ssts.ssts.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    //세큐리티 추가시 동작가능
    //private final PasswordEncoder passwordEncoder;
    private final SeriesRepository seriesRepository;

    /*
    * 회원가입 용 api
    * 권한 : ADMIN, USER
    * */
    public Member saveMember(MemberSignUpPostDto memberSignUpPostDto) {
        //ouath 로만 로그인 구현하면.. password 필요없는거 아냐..? 나중에 구현 후 생각해보기.
        Member member=Member.of(memberSignUpPostDto.getEmail());

        verifyExistsEmail(member.getEmail());

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

    /*
    * 마이페이지 정보
    * 권한 : ADMIN, USER
    * 응답 : 유저정보(nickName, image, introduce)
    *  */
    public MemberMyPageResponseDto getMyPageMemberInfo() {

        long memberId= SecurityUtil.getMemberId();
        Member member = findMemberById(memberId);
        MemberMyPageResponseDto responseDto=MemberMyPageResponseDto.of(member.getNickName(),
                member.getImage(),
                member.getIntroduce());

        return responseDto;
    }

    public Member findMemberById(long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        log.info("하늘 member service : member nickName="+member.getNickName()+" 조회");

        return member;
    }

    @Transactional
    public Member changeMemberStatusWithdraw() {
        long memberId= SecurityUtil.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        member.setDeletedAt(LocalDateTime.now());
        member.setStatus(Member.Status.WITHDRAW);

        log.info("하늘 member service : memberid="+memberId+", "+member.getDeletedAt()+"에 탈퇴처리");

        return member;
    }

    @Transactional
    public MemberEditInfoResponseDto updateMemberInfo(MemberEditInfoPatchDto memberEditInfoPatchDto) {
        long memberId=SecurityUtil.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        //if문을 왜 쓰느냐? --> 입력안한 값에는 null이 들어가고, DB에 저장되어 버린다. 쌈바?..??훔..

        member.setImage(memberEditInfoPatchDto.getImage());
        member.setNickName(memberEditInfoPatchDto.getNickName());
        member.setIntroduce(memberEditInfoPatchDto.getIntroduce());

        log.info("하늘 member service : 수정 후 [" +
                " nickName="+member.getNickName()+
                " image="+member.getImage()+
                " introduce="+member.getIntroduce()+"]");

        MemberEditInfoResponseDto responseDto = MemberEditInfoResponseDto.of(member.getImage(), member.getNickName(), member.getIntroduce());
        return responseDto;
    }

    public void verifyExistsEmail(String email) {

        //내가 만든 Exception을 던지려면 supplier 타입으로 던져야한다.
        Optional<Member> member=memberRepository.findByEmail(email);
        //멤버가 증명됬다는 의미로 로그를 띄워줘야할까?
        if (member.isPresent()) {
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
