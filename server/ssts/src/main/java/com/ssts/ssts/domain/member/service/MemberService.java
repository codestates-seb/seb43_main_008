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
import com.ssts.ssts.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    private final S3Uploader s3ImageUploader;

    public Member saveMember(MemberSignUpPostDto memberSignUpPostDto) {
        //ouath 로만 로그인 구현하면.. password 필요없는거 아냐..? 나중에 구현 후 생각해보기.
        Member member=Member.of(memberSignUpPostDto.getNickName(),
                memberSignUpPostDto.getEmail(),
                memberSignUpPostDto.getPassword());
        member.setImage(s3ImageUploader.getS3("ssts-img", "member/default.png"));

        verifyExistsEmail(member.getEmail());
        // 중복되지 않는 이메일이라면 그때 비밀번호 암호화하기
        //member.setPassword(passwordEncoder.encode(member.getPassword());
        //

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

    public MemberMyPageResponseDto findMember(long memberId) {

        Member member = findMemberById(memberId);
        MemberMyPageResponseDto responseDto=MemberMyPageResponseDto.of(member.getNickName(),
                member.getImage(),
                member.getIntroduce(),
                seriesRepository.findAllByMemberId(member.getId())); // 데이터 가져오기

        return responseDto;
    }

    public Member findMemberById(long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return member;
    }

    @Transactional
    public Member changeMemberStatusWithdraw(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        member.setStatus(Member.Status.WITHDRAW);
        //탈퇴 로그 출력
        log.debug("memberid="+member.getId()+" 탈퇴처리");

        return member;
    }

    @Transactional
    public MemberEditInfoResponseDto editMemberInfo(long memberId, MemberEditInfoPatchDto memberEditInfoPatchDto, MultipartFile image) throws IOException {
        Member member = memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        if(!image.isEmpty()){
            String saveFileName = s3ImageUploader.upload(image, "member");
            member.setImage(saveFileName);
        }
        // member.setImage(memberEditInfoPatchDto.getImage()); 이미지 로직 추가 후 수정
        member.setNickName(memberEditInfoPatchDto.getNickName());
        //member.setPassword(memberEditInfoPatchDto.getPassword()); //oauth로그인이라서 필요없다.
        member.setIntroduce(memberEditInfoPatchDto.getIntroduce());

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
