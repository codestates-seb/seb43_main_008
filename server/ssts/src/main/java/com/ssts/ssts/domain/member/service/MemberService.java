package com.ssts.ssts.domain.member.service;

import com.ssts.ssts.domain.follow.repository.FollowRepository;
import com.ssts.ssts.domain.follow.service.FollowService;
import com.ssts.ssts.domain.member.constant.MemberConstants;
import com.ssts.ssts.domain.member.constant.MemberStatus;
import com.ssts.ssts.domain.member.dto.FeedResponse;
import com.ssts.ssts.domain.member.dto.MemberFeedResponse;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.global.auth.utils.SocialType;
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
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3Uploader s3ImageUploader;
    private final FollowRepository followRepository;
    //private final FollowService followService;

    public Member getMemberByToken() {
        long memberId = SecurityUtil.getMemberId();
        Member member = memberRepository.findByIdAndMemberStatus(memberId, MemberStatus.ACTIVE).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return member;
    }

    public Optional<Member> findOptionalMemberByEmail(String email) {
        checkStringIsValid(email);
        Optional<Member> member = memberRepository.findByEmail(email);
        return member;
    }

    public Member findMemberByNickName(String nickName) {
        checkStringIsValid(nickName);
        Member member = memberRepository.findByNickName(nickName).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return member;
    }

    //FIXME 조건문 합칠 수 있는지 확인하기
    public Optional<Member> findOptionalMemberByEmailAndSocialType(String email, SocialType socialType) {
        checkStringIsValid(email);
        Optional<Member> member = findOptionalMemberByEmail(email);
        if (member.isPresent()) {
            if (member.get().getSocialType() != socialType)
                throw new BusinessLogicException(ExceptionCode.EMAIL_DUPLICATE);
        }
        return member;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) throw new BusinessLogicException(ExceptionCode.EMAIL_DUPLICATE);
    }

    private void verifyExistsPhoneNumber(String phone) {
        Optional<Member> member = memberRepository.findByPhone(phone);
        if (member.isPresent()) throw new BusinessLogicException(ExceptionCode.PHONENUMBER_DUPLICATE);
    }

    private void verifyExistsNickName(String nickName) {
        Optional<Member> member = memberRepository.findByNickName(nickName);
        if (member.isPresent()) throw new BusinessLogicException(ExceptionCode.NICKNAME_DUPLICATE);
    }

    // null 인지 "" 인지만 검사
    private void checkStringIsValid(String str) {
        if (str == null || str.isEmpty()) throw new BusinessLogicException(ExceptionCode.INPUT_NULL);
    }

    public Member saveMember(String email, String nickName, String phone, SocialType socialType) {
        checkStringIsValid(email);
        checkStringIsValid(nickName);
        checkStringIsValid(phone);

        verifyExistsEmail(email);
        verifyExistsNickName(nickName);
        verifyExistsPhoneNumber(phone);

        Member member = Member.of(email, nickName, phone);
        member.setImage(s3ImageUploader.getS3(MemberConstants.bucketName, MemberConstants.fileName));
        member.setRoles(createRoles(member));
        member.setSocialType(socialType);

        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    private List<String> createRoles(Member member) {
        if (member.getEmail().contains(MemberConstants.adminEmail))
            return List.of(MemberConstants.roleAdmin, MemberConstants.roleUser);
        else
            return List.of(MemberConstants.roleUser);
    }

    public void testDeleteMember(Long memberId) {
        if (memberId == null) throw new BusinessLogicException(ExceptionCode.INPUT_NULL);
        memberRepository.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        memberRepository.deleteById(memberId);
    }

    @Transactional
    public Member changeMemberStatusWithdraw() {
        Member member = getMemberByToken();
        member.setDeletedAt(LocalDateTime.now());
        member.setMemberStatus(MemberStatus.WITHDRAW);
        return member;
    }

    public MemberFeedResponse getMemberFeedInfo(String nickName) {
        Member target = memberRepository.findByNickName(nickName).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Member member = getMemberByToken();
        //FIXME -> followService 사용시 순환참조 발생!
        //boolean isFollowedMember = followService.isMemberFollowing(member.getId(), target.getId());
        boolean isFollowedMember = followRepository.existsByFollowerIdAndFollowingId(member.getId(), target.getId());
        MemberFeedResponse responseDto = MemberFeedResponse.of(
                target.getNickName(),
                target.getImage(),
                target.getIntroduce(),
                isFollowedMember);

        return responseDto;
    }

    public FeedResponse getMyFeedInfo() {
        Member member = getMemberByToken();
        FeedResponse responseDto = FeedResponse.of(
                member.getNickName(),
                member.getImage(),
                member.getIntroduce());
        return responseDto;
    }

    @Transactional
    public FeedResponse updateMyFeedInfo(String nickName, Optional<MultipartFile> image, String introduce) throws IOException {
        checkUpdateIsAvailable(nickName, image, introduce);
        Member member = getMemberByToken();

        if (nickName != null && !member.getNickName().equals(nickName))
            member.setNickName(nickName);

        if (image.isPresent() && !(image.get().getName().equals(""))) {
            String saveFileName = s3ImageUploader.upload(image.get(), "member");
            member.setImage(saveFileName);
        }
        if (introduce != null)
            member.setIntroduce(introduce);

        FeedResponse response = FeedResponse.of(member.getNickName(), member.getImage(), member.getIntroduce());
        return response;
    }

    private void checkUpdateIsAvailable(String nickName, Optional<MultipartFile> image, String introduce) {
        String pattern = "^[가-힣a-zA-Z0-9]+$";
        if (nickName != null && !Pattern.matches(pattern, nickName))
            throw new BusinessLogicException(ExceptionCode.NICKNAME_NOT_AVAILABLE);

        //null값 검사 : 1. 세 변수 다 값이 안들어오면 수정값이 없어서 서비스할 필요 없음. -> 예외
        if ((nickName == null && !image.isPresent() && introduce == null)
                || (nickName != null && nickName.isEmpty()) // 2. nickName이 null이 아닌데, 입력값이 "" 이면 예외
                || (image.isPresent() && image.get().getOriginalFilename().equals(""))) // 3. image가 null이 아닌데, 입력값이 "" 이면 예외
            throw new BusinessLogicException(ExceptionCode.INPUT_NULL);
    }

    /*
     * 탈퇴한 회원인지 반환한다.
     * false 값일 경우 회원가입을 진행한다.
     * FIXME 나중에 서버에서 DB를 관리할 때 6개월 이상된 데이터는 자동 삭제할 예정이라서 굳이 시간계산을 할 필요없다.
     * */
    private boolean verifyIsWithdrawMember(Member member) {
        LocalDateTime currentTime = LocalDateTime.now();

        if (member.getMemberStatus() == MemberStatus.WITHDRAW) {
            // 탈퇴한 시간 + 6개월 <= 현재시간
            if (currentTime.isAfter(member.getDeletedAt().plusMonths(6))) {
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

}
