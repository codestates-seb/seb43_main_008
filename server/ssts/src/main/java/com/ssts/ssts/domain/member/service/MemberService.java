package com.ssts.ssts.domain.member.service;

import com.ssts.ssts.domain.member.dto.MemberEditInfoPatchDto;
import com.ssts.ssts.domain.member.dto.MemberFeedResponseDto;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.S3Uploader;
import com.ssts.ssts.global.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final S3Uploader s3ImageUploader;


    /*
    * 토큰에서 id 가져와서 Member 반환
    * */
    public Member findMemberByToken() {

        long memberId = SecurityUtil.getMemberId();
        Member member = memberRepository.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        log.info("하늘/member service : token(id) -> member 조회 " +
                "\nmemberid=" + member.getId());

        return member;
    }

    public Member findMemberByNickName(String nickName) {
        Member member = memberRepository.findByNickName(nickName).
                orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        log.info("하늘/member service : nickName -> member 조회" +
                "\nmemberid=" + member.getId()+
                "\nnickName="+member.getNickName());
        return member;
    }

    public Member findMemberById(long memberId){

        Member member = memberRepository.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        log.info("하늘/member service : id -> member 조회 " +
                "\nmemberid=" + member.getId());

        return member;
    }

    public Optional<Member> findMemberByEmail(String email){

        Optional<Member> member = memberRepository.findByEmail(email);

        log.info("하늘 member service : email -> (optional)member 조회" +
                "\nemail=" + email);

        return member;
    }

    /*
    * DB에 멤버 등록
    * */
    public Member saveMember(String email, String nickName, String phone ) {

        log.info("하늘/member service : save 회원가입 전 기입정보 " +
                "\nemail="+email+
                "\nphone="+phone+
                "\nnickName="+nickName);

        Member member = Member.of(email, nickName, phone);
        member.setImage(s3ImageUploader.getS3("ssts-img", "member/default.png"));
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

        log.info("하늘/member service : save 회원가입 완료" +
                "\nmemberId="+savedMember.getId());

        return savedMember;
    }

    /*
    * 테스트용 멤버 삭제
    * */
    public void testDeleteMember(long memberId){

        memberRepository.deleteById(memberId);
        log.info("하늘/member service : test delete 테스트 삭제" +
                "\nmemberId="+memberId);
    }


    public MemberFeedResponseDto getMemberFeedInfo(String nickName){

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

        log.info("하늘/member service : withdraw 탈퇴" +
                "\nmemberid=" + member.getId() +
                "\ndeletedAt="+member.getDeletedAt());

        return member;
    }

    @Transactional
    public MemberFeedResponseDto updateMyFeedInfo(MemberEditInfoPatchDto memberEditInfoPatchDto, Optional<MultipartFile> image) throws IOException{

        Member member = findMemberByToken();
        String nickName=memberEditInfoPatchDto.getNickName();

        String introduce= memberEditInfoPatchDto.getIntroduce();
        //if문을 왜 쓰느냐? --> 입력안한 값에는 null이 들어가고, DB에 저장되어 버린다. 쌈바?..??훔..
        if (!(nickName == null)) {

            verifyExistsNickName(nickName);
            member.setNickName(memberEditInfoPatchDto.getNickName());
        }
        if(image.isPresent()){

            String saveFileName = s3ImageUploader.upload(image.get(),"daylog");
            member.setImage(saveFileName);
        }
        if (!(introduce == null)) {

            member.setIntroduce(memberEditInfoPatchDto.getIntroduce());
        }

        log.info("하늘/member service : update 업데이트 후 피드" +
                "\nnickName=" + member.getNickName() +
                "\nimage=" + member.getImage() +
                "\nintroduce=" + member.getIntroduce());

        MemberFeedResponseDto responseDto = MemberFeedResponseDto.of(
                member.getNickName(),
                member.getImage(),
                member.getIntroduce());

        return responseDto;
    }

    public Member signUpMember(String phone, String nickName) {

        String email=SecurityUtil.getMemberEmail();
        Member member=saveMember(email, phone, nickName);

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
