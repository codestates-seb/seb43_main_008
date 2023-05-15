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

    /*
    * 토큰에서 id 가져와서 Member 반환
    * */
    public Member findMemberByToken() {

        long memberId = SecurityUtil.getMemberId();
        Member member = memberRepository.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        log.info("하늘 member service : memberid=" + member.getId() + " 조회");

        return member;
    }

    public Member findMemberById(long memberId){

        long findId = SecurityUtil.getMemberId();
        Member member = memberRepository.findById(findId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        log.info("하늘 member service : member id=" + member.getId() + " 조회");

        return member;
    }

    public Optional<Member> findMemberByEmail(String email){

        Optional<Member> member = memberRepository.findByEmail(email);

        log.info("하늘 member service : member email=" + email + " 조회, 반환값 optional");

        return member;
    }

    /*
    * 테스트용 회원가입
    * */
    public Member saveMember(MemberSignUpPostDto memberSignUpPostDto) {

        Member member = Member.of(memberSignUpPostDto.getEmail(), memberSignUpPostDto.getNickName(), memberSignUpPostDto.getPhone());

        verifyExistsEmail(member.getEmail());
        verifyExistsNickName(member.getNickName());
        verifyExistsPhoneNumber(member.getPhone());

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

    /*
    * 테스트용 멤버 삭제
    * */
    public void deleteMember(long memberId){

        memberRepository.deleteById(memberId);
        log.info("하늘 member service test delete : memberId="+memberId);
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


    public MemberFeedResponseDto getMyFeedInfo() {

        Member member = findMemberByToken();
        MemberFeedResponseDto responseDto = MemberFeedResponseDto.of(
                member.getNickName(),
                member.getImage(),
                member.getIntroduce());

        return responseDto;
    }

    @Transactional
    public Member changeMemberStatusWithdraw() {

        Member member = findMemberByToken();
        member.setDeletedAt(LocalDateTime.now());
        member.setStatus(Member.Status.WITHDRAW);

        log.info("하늘 member service : memberid=" + member.getId() +
                ", " + member.getDeletedAt() + "에 탈퇴처리");

        return member;
    }

    @Transactional
    public MemberFeedResponseDto updateMyFeedInfo(MemberEditInfoPatchDto memberEditInfoPatchDto) {

        Member member = findMemberByToken();
        String nickName=memberEditInfoPatchDto.getNickName();
        String image=memberEditInfoPatchDto.getImage();
        String introduce= memberEditInfoPatchDto.getIntroduce();

        //if문을 왜 쓰느냐? --> 입력안한 값에는 null이 들어가고, DB에 저장되어 버린다. 쌈바?..??훔..
        if (!(nickName == null)) {

            verifyExistsNickName(nickName);
            member.setNickName(memberEditInfoPatchDto.getNickName());
        }
        if (!(image == null)) {

            member.setImage(memberEditInfoPatchDto.getImage());
        }
        if (!(introduce == null)) {

            member.setIntroduce(memberEditInfoPatchDto.getIntroduce());
        }

        log.info("하늘 member service : 수정 후 [" +
                " nickName=" + member.getNickName() +
                " image=" + member.getImage() +
                " introduce=" + member.getIntroduce() + "]");

        MemberFeedResponseDto responseDto = MemberFeedResponseDto.of(
                member.getNickName(),
                member.getImage(),
                member.getIntroduce());
        return responseDto;
    }

    @Transactional
    public Member updatePhoneInfo(MemberPhoneInfoPostDto memberPhoneInfoPostDto) {

        Member member = findMemberByToken();
        verifyExistsPhoneNumber(memberPhoneInfoPostDto.getPhone());
        member.setPhone(memberPhoneInfoPostDto.getPhone());

        log.info("하늘 member service : phone="+member.getPhone()+" 입력");

        return member;
    }

    public void verifyExistsEmail(String email) {

        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_DUPLICATE);
        }

    }

    public void verifyExistsPhoneNumber(String phone){

        Optional<Member> member = memberRepository.findByPhone(phone);

        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.PHONENUMBER_DUPLICATE);
        }
    }

    public void verifyExistsNickName(String nickName){
        Optional<Member> member = memberRepository.findByNickName(nickName);

        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.NICKNAME_DUPLICATE);
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