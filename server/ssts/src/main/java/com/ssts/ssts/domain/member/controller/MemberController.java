package com.ssts.ssts.domain.member.controller;


import com.ssts.ssts.domain.member.dto.*;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;



    /*
    * 테스트용 멤버 회원가입 기능
    * 권한 : ADMIN
    * */
    @PostMapping("/test/signup")
    public ResponseEntity createMember(@RequestBody MemberSignUpPostDto memberSignUpPostDto) {

        Member member = memberService.saveMember(memberSignUpPostDto);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /*
     * 테스트용 멤버 회원 삭제 기능
     * 권한 : ADMIN
     * */
    @DeleteMapping("/test/{memberId}")
    public ResponseEntity deleteMember(@PathVariable long memberId) {

        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }



    /*
     * 회원가입 절차-멤버 핸드폰 번호 입력(인증과정 API가 되어야한다)
     * 권한 : USER, ADMIN
     * */
    //FIXME 핸드폰번호,이메일,닉네임이.. 다 넘어와야 하는데..어..
    //FIXME [보안문제] 이거 휴대폰 인증 API안쓰면 그냥 번호가 노출되서 나중에 무조건 고쳐야한다.
    @PostMapping("/signup/phone")
    public ResponseEntity inputMemberPhone(@RequestBody MemberPhoneInfoPostDto memberPhoneInfoPostDto){

        Member member=memberService.updatePhoneInfo(memberPhoneInfoPostDto);

        //FIXME 출력도 고쳐야한다.
        return ResponseEntity.status(HttpStatus.OK).body(member.getPhone()+" 휴대폰 번호가 정상적으로 입력되셨습니다.");

    }



    /*
    * 멤버 본인 피드 조회 기능
    * 권한 : USER, ADMIN
    * */
    @GetMapping("/feed")
    public ResponseEntity<MemberFeedResponseDto> getMyFeed() {

        MemberFeedResponseDto response = memberService.getMyFeedInfo();

        return ResponseEntity.ok(response);
    }

    /*
     * 상대 멤버 피드 조회 기능
     * 권한 : USER, ADMIN
     * */
    @GetMapping("/feed/{nickName}")
    public ResponseEntity<MemberFeedResponseDto> getFeed(@PathVariable String nickName) {

        MemberFeedResponseDto response = memberService.getFeedInfo(nickName);

        return ResponseEntity.ok(response);
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
    * 멤버 정보 수정
    * 권한 : USER, ADMIN
    * */
    @PatchMapping("/feed")
    public ResponseEntity updateMemberInfo(@ModelAttribute MemberEditInfoPatchDto memberEditInfoPatchDto,
                                           @RequestPart("image")MultipartFile image) throws IOException{


        MemberFeedResponseDto response = memberService.updateMyFeedInfo(memberEditInfoPatchDto, image);
        // 요청 body값이 nickname은 반드시 들어가야한다,image랑 introduce는 nullable이라서 선택적.

        //변경됬으니까 변경된 입력값을 알려줘야 한다.
        return ResponseEntity.ok(response);
    }



}
