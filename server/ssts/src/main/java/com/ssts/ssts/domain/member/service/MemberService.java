package com.ssts.ssts.domain.member.service;

import com.ssts.ssts.domain.follow.repository.FollowRepository;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final FollowRepository followRepository;

    private final S3Uploader s3ImageUploader;


    /*
    * 토큰에서 id 가져와서 Member 반환
    * */
    public Member findMemberByToken() {

        //null값 검사 : getMemberId는 long 타입 반환.
        long memberId = SecurityUtil.getMemberId();

        Member member = memberRepository.findByIdAndMemberStatus(memberId, MemberStatus.ACTIVE).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        log.info("하늘/member service : token(id) -> member 조회" +
                "\nmemberid=" + member.getId());

        return member;
    }

    public Member findMemberByNickName(String nickName) {

        //null값 검사
        if (nickName.isEmpty())
            throw new BusinessLogicException(ExceptionCode.INPUT_NULL);

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

        log.info("하늘/member service : id -> member 조회" +
                "\nmemberid=" + member.getId());

        return member;
    }

    public Optional<Member> findMemberByEmailAndSocialType(String email, SocialType socialType){

        if (email==null || email.isEmpty() || socialType==null )
            throw new BusinessLogicException(ExceptionCode.INPUT_NULL);

        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()){
            if(member.get().getSocialType() != socialType)
                throw new BusinessLogicException(ExceptionCode.EMAIL_DUPLICATE);
        }

        return member;
    }

    public Optional<Member> findMemberByEmail(String email){

        if (email==null || email.isEmpty())
            throw new BusinessLogicException(ExceptionCode.INPUT_NULL);

        Optional<Member> member = memberRepository.findByEmail(email);

        log.info("하늘 member service : email -> (optional)member 조회" +
                "\nemail=" + email);

        return member;
    }

    /*
    * DB에 멤버 등록 - test랑 실제 서비스 둘다 사용
    * */
    public Member saveMember(String email, String nickName, String phone, SocialType socialType) {

        //null값 검사 : 세 변수 다 필수 입력값.
        if (email.isEmpty() || nickName.isEmpty() || phone.isEmpty())
            throw new BusinessLogicException(ExceptionCode.INPUT_NULL);

        log.info("하늘/member service : save 회원가입 전 기입정보" +
                "\nemail="+email+
                "\nphone="+phone+
                "\nnickName="+nickName);

        Member member = Member.of(email, nickName, phone);

        member.setImage(s3ImageUploader.getS3("ssts-img", "member/default.png"));

        verifyExistsEmail(member.getEmail());
        verifyExistsNickName(member.getNickName());
        verifyExistsPhoneNumber(member.getPhone());

        member.setRoles(createRoles(member));
        member.setSocialType(socialType);

        Member savedMember = memberRepository.save(member);

        log.info("하늘/member service : save 회원가입 완료" +
                "\nmemberId="+savedMember.getId());

        return savedMember;
    }

    /*
    * 테스트용 멤버 삭제
    * */
    public void testDeleteMember(Long memberId){
        log.info("하늘/member service : test delete 테스트 삭제" +
                "\nmemberId="+memberId);

        //null값 검사
        if (memberId == null)
            throw new BusinessLogicException(ExceptionCode.INPUT_NULL);

        findMemberById(memberId);

        memberRepository.deleteById(memberId);

    }


    public MemberFeedResponse getMemberFeedInfo(String nickName){

        Member target = memberRepository.findByNickName(nickName).
                orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Member member = findMemberByToken();
        //FIXME -> followService 사용시 순환참조 발생!
        //boolean isFollowedMember= followService.isMemberFollowing(member.getId(), target.getId());
        boolean isFollowedMember=followRepository.existsByFollowerIdAndFollowingId(member.getId(), target.getId());
        MemberFeedResponse responseDto = MemberFeedResponse.of(
                target.getNickName(),
                target.getImage(),
                target.getIntroduce(),
                isFollowedMember);

        return responseDto;
    }


    public FeedResponse getMyFeedInfo() {

        Member member = findMemberByToken();
        FeedResponse responseDto = FeedResponse.of(
                member.getNickName(),
                member.getImage(),
                member.getIntroduce());

        return responseDto;
    }

    @Transactional
    public Member changeMemberStatusWithdraw() {

        Member member = findMemberByToken();
        member.setDeletedAt(LocalDateTime.now());
        member.setMemberStatus(MemberStatus.WITHDRAW);

        log.info("하늘/member service : withdraw 탈퇴" +
                "\nmemberid=" + member.getId() +
                "\ndeletedAt="+member.getDeletedAt());

        return member;
    }

    @Transactional
    public FeedResponse updateMyFeedInfo(String nickName, Optional<MultipartFile> image, String introduce) throws IOException{

        //null값 검사 : 1. 세 변수 다 값이 안들어오면 수정값이 없어서 서비스할 필요 없음. -> 예외
        if ( (nickName == null && !image.isPresent() && introduce == null)
                || (nickName != null && nickName.isEmpty()) // 2. nickName이 null이 아닌데, 입력값이 "" 이면 예외
                || (image.isPresent() && image.get().getOriginalFilename().equals(""))) // 3. image가 null이 아닌데, 입력값이 "" 이면 예외
            throw new BusinessLogicException(ExceptionCode.INPUT_NULL);

        Member member = findMemberByToken();

        if (nickName != null && !(nickName.isEmpty())) {

            if (!(member.getNickName().equals(nickName))) {
                // 원래 본인의 닉네임과 다른 닉네임이라면
                verifyExistsNickName(nickName);
                member.setNickName(nickName);
            }

        }
        if (image.isPresent() && !(image.get().getName().equals(""))) {

            String saveFileName = s3ImageUploader.upload(image.get(),"member");
            member.setImage(saveFileName);
        }
        if (introduce != null) {

            member.setIntroduce(introduce);
        }

       /* updateUtils.copyNonNullProperties(nickName,member.getNickName());
        updateUtils.copyNonNullProperties(image,member.getImage());
        updateUtils.copyNonNullProperties(introduce,member.getIntroduce());*/

        log.info("하늘/member service : update 업데이트 후 피드" +
                "\nnickName=" + member.getNickName() +
                "\nimage=" + member.getImage() +
                "\nintroduce=" + member.getIntroduce());

        FeedResponse responseDto = FeedResponse.of(
                member.getNickName(),
                member.getImage(),
                member.getIntroduce());

        return responseDto;
    }

    public Member signUpMember(String phone, String nickName, String socialType) {

        //null값 검사 : 두 변수 다 필수 입력값.
        if (nickName.isEmpty() || phone.isEmpty())
            throw new BusinessLogicException(ExceptionCode.INPUT_NULL);

        String email=SecurityUtil.getMemberEmail();
        Member member=saveMember(email, nickName, phone, SocialType.stringToSocialType(socialType.toLowerCase()));

        return member;
    }


    /*
    * 탈퇴한 회원인지 반환한다.
    * false 값일 경우 회원가입을 진행한다.
    * FIXME 나중에 서버에서 DB를 관리할 때 6개월 이상된 데이터는 자동 삭제할 예정이라서 굳이 시간계산을 할 필요없다.
    * */
    private boolean verifyIsWithdrawMember(Member member){
        LocalDateTime currentTime = LocalDateTime.now();

        if(member.getMemberStatus()==MemberStatus.WITHDRAW){
            // 탈퇴한 시간 + 6개월 <= 현재시간
            if(currentTime.isAfter(member.getDeletedAt().plusMonths(6))){
                return true;
            }else{
                return false;
            }
        }

        return true;
    }

    private void verifyExistsEmail(String email) {

        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_DUPLICATE);
        }

    }

    private void verifyExistsPhoneNumber(String phone){

        Optional<Member> member = memberRepository.findByPhone(phone);

        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.PHONENUMBER_DUPLICATE);
        }
    }

    private void verifyExistsNickName(String nickName){
        Optional<Member> member = memberRepository.findByNickName(nickName);

        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.NICKNAME_DUPLICATE);
        }
    }

    private List<String> createRoles(Member member){
        if (isAdmin(member.getEmail())) {
            return List.of(MemberConstants.ROLE_ADMIN.getConstant(), MemberConstants.ROLE_USER.getConstant());
        } else {
            return List.of(MemberConstants.ROLE_USER.getConstant());
        }
    }

    private boolean isAdmin(String email) {

        if (email.contains(MemberConstants.ADMIN_EMAIL.getConstant())) {
            return true;
        } else {
            return false;
        }
    }
}
