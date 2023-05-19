package com.ssts.ssts.domain.member.controller;


import com.ssts.ssts.domain.badges.response.BadgeResponse;
import com.ssts.ssts.domain.badges.service.BadgeService;
import com.ssts.ssts.domain.member.dto.MemberSignUpAddInfoDto;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.dto.MemberEditInfoPatchDto;
import com.ssts.ssts.domain.member.dto.MemberFeedResponseDto;
import com.ssts.ssts.domain.member.dto.MemberTestSignUpDto;
import com.ssts.ssts.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //badgeFeed
    private final BadgeService badgeService;


    /*
    * 테스트용 멤버 회원가입 기능
    * 권한 : ADMIN
    * */
    @PostMapping("/test/signup")
    public ResponseEntity testCreateMember(@RequestBody MemberTestSignUpDto memberTestSignUpDto) {

        Member member = memberService.saveMember(
                memberTestSignUpDto.getEmail(),
                memberTestSignUpDto.getNickName(),
                memberTestSignUpDto.getPhone());

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /*
     * 테스트용 멤버 회원 삭제 기능
     * 권한 : ADMIN
     * */
    @DeleteMapping("/test/{memberId}")
    public ResponseEntity testDeleteMember(@PathVariable long memberId) {

        memberService.testDeleteMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }



    /*
     * 회원가입
     * 권한 : USER, ADMIN
     * */
    //FIXME 핸드폰번호,이메일,닉네임이.. 다 넘어와야 하는데..어..
    //FIXME [보안문제] 이거 휴대폰 인증 API안쓰면 그냥 번호가 노출되서 나중에 무조건 고쳐야한다.
    //https://lasbe.tistory.com/132
    @PostMapping("/signup")
    public ResponseEntity signUpMember(@RequestBody MemberSignUpAddInfoDto memberSignUpAddInfoDto){

        Member member=memberService.signUpMember(memberSignUpAddInfoDto.getPhone(), memberSignUpAddInfoDto.getNickName());

        //FIXME 출력도 고쳐야한다.
        return ResponseEntity.status(HttpStatus.OK).body(
                member.getNickName()+"님! 회원가입이 끝났어요.");

    }


    /*
     * 멤버 탈퇴 기능
     * 권한 : USER, ADMIN
     * */
    @DeleteMapping("/members")
    public ResponseEntity withdrawMember() {

        Member member=memberService.changeMemberStatusWithdraw();

        return ResponseEntity.status(HttpStatus.OK).body(member.getDeletedAt()+"에 탈퇴처리되셨습니다. " +
                "정책에 따라 6개월 보관할 예정하겠습니다 :) ");
    }

    /*
    * 멤버 본인 피드 조회 기능
    * 권한 : USER, ADMIN
    * */
    @GetMapping("/feed")
    public ResponseEntity<MemberFeedResponseDto> getMyFeedInfo() {

        MemberFeedResponseDto response = memberService.getMyFeedInfo();

        return ResponseEntity.ok(response);
    }

    /*
     * 상대 멤버 피드 조회 기능
     * 권한 : USER, ADMIN
     * */
    @GetMapping("/feed/{nickName}")
    public ResponseEntity<MemberFeedResponseDto> getMemberFeedInfo(@PathVariable String nickName) {

        MemberFeedResponseDto response = memberService.getMemberFeedInfo(nickName);

        return ResponseEntity.ok(response);
    }


    /*
    * 멤버 본인 피드 정보 수정 기능
    * 권한 : USER, ADMIN
    * */
    @PatchMapping("/feed")
    public ResponseEntity updateMyFeedInfo(@ModelAttribute MemberEditInfoPatchDto memberEditInfoPatchDto,
                                           @RequestPart(value = "image", required = false) Optional<MultipartFile> image) throws IOException{


        MemberFeedResponseDto response = memberService.updateMyFeedInfo(memberEditInfoPatchDto, image);

        //변경됬으니까 변경된 입력값을 알려줘야 한다.
        return ResponseEntity.ok(response);
    }


    /* 상대 멤버 뱃지 피드 조회 기능
    * */
    @GetMapping("/feed/badge/{nickname}")
    public ResponseEntity getYourBadges(@PathVariable("nickname") String nickname){
        List<BadgeResponse> response = badgeService.findYourBadges(nickname);
        return new ResponseEntity(response, HttpStatus.OK);
    }



}
