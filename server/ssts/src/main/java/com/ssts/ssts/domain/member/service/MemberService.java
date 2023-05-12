package com.ssts.ssts.domain.member.service;

import com.ssts.ssts.domain.member.dto.*;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.repository.MemberRepository;
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

    public Member findMember() {

        long memberId = SecurityUtil.getMemberId();
        Member member = memberRepository.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        log.info("하늘 member service : memberid=" + member.getId() + " 조회");

        return member;
    }

    public Member saveMember(MemberSignUpPostDto memberSignUpPostDto) {

        Member member = Member.of(memberSignUpPostDto.getEmail());

        verifyExistsEmail(member.getEmail());

        List<String> roles;
        if (isAdmin(member.getEmail())) {
            roles = List.of("ADMIN", "USER");
        } else {
            roles = List.of("USER");
        }
        member.setRoles(roles);

        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    public MemberFeedResponseDto getMyFeedInfo() {

        Member member = findMember();
        MemberFeedResponseDto responseDto = MemberFeedResponseDto.of(
                member.getNickName(),
                member.getImage(),
                member.getIntroduce());

        return responseDto;
    }

    public MemberFeedResponseDto getFeedInfo(String nickName){

        Member member = memberRepository.findByNickName(nickName).
                orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        MemberFeedResponseDto responseDto = MemberFeedResponseDto.of(
                member.getNickName(),
                member.getImage(),
                member.getIntroduce());

        return responseDto;
    }


    @Transactional
    public Member changeMemberStatusWithdraw() {

        Member member = findMember();
        member.setDeletedAt(LocalDateTime.now());
        member.setStatus(Member.Status.WITHDRAW);

        log.info("하늘 member service : memberid=" + member.getId() +
                ", " + member.getDeletedAt() + "에 탈퇴처리");

        return member;
    }

    @Transactional
    public MemberFeedResponseDto updateMyFeedInfo(MemberEditInfoPatchDto memberEditInfoPatchDto) {

        Member member = findMember();

        //if문을 왜 쓰느냐? --> 입력안한 값에는 null이 들어가고, DB에 저장되어 버린다. 쌈바?..??훔..
        member.setImage(memberEditInfoPatchDto.getImage());
        member.setNickName(memberEditInfoPatchDto.getNickName());
        member.setIntroduce(memberEditInfoPatchDto.getIntroduce());

        log.info("하늘 member service : 수정 후 [" +
                " nickName=" + member.getNickName() +
                " image=" + member.getImage() +
                " introduce=" + member.getIntroduce() + "]");

        MemberFeedResponseDto responseDto = MemberFeedResponseDto.of(
                member.getImage(),
                member.getNickName(),
                member.getIntroduce());
        return responseDto;
    }

    @Transactional
    public Member updatePhoneInfo(MemberPhoneInfoPostDto memberPhoneInfoPostDto) {

        Member member = findMember();

        member.setPhone(memberPhoneInfoPostDto.getPhone());

        log.info("하늘 member service : phone="+member.getPhone()+" 입력");

        return member;
    }

    public void verifyExistsEmail(String email) {

        //내가 만든 Exception을 던지려면 supplier 타입으로 던져야한다.
        Optional<Member> member = memberRepository.findByEmail(email);
        //멤버가 증명됬다는 의미로 로그를 띄워줘야할까?
        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_DUPLICATE);
        }

    }

    public void verifyExistsPhoneNumber(String phone){
        Optional<Member> member = memberRepository.findByEmail(phone);
        //멤버가 증명됬다는 의미로 로그를 띄워줘야할까?
        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.PHONENUMBER_DUPLICATE);
        }
    }

    private boolean isAdmin(String email) {
        if (email.contains("@ssts.com")) {
            return true;
        } else {
            return false;
        }
    }
}