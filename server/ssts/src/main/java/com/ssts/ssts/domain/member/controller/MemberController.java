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
import java.util.Optional;

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
    public ApiResponse testCreateMember(@RequestBody MemberTestSignUpDto memberTestSignUpDto) {

        Member member = memberService.saveMember(
                memberTestSignUpDto.getEmail(),
                memberTestSignUpDto.getNickName(),
                memberTestSignUpDto.getPhone());

        return ApiResponse.ok();
    }

    /*
     * 테스트용 멤버 회원 삭제 기능
     * 권한 : ADMIN
     * */
    @DeleteMapping("/test/{memberId}")
    public ApiResponse testDeleteMember(@PathVariable long memberId) {

        memberService.testDeleteMember(memberId);
        //return ResponseEntity.status(HttpStatus.OK).body(null);
        return ApiResponse.ok();
    }


    /*
     * 멤버 탈퇴 기능
     * 권한 : USER, ADMIN
     * */
    @DeleteMapping("/members")
    public ApiResponse withdrawMember() {

        Member member=memberService.changeMemberStatusWithdraw();

        //return ResponseEntity.status(HttpStatus.OK).body(member.getDeletedAt()+"에 정상적으로 탈퇴처리되셨습니다. " +
        //        "정책에 따라 6개월 보관 하겠습니다 :) ");
        return ApiResponse.ok(member.getDeletedAt()+"에 정상적으로 탈퇴처리되셨습니다. " +
                        "정책에 따라 6개월 보관 하겠습니다 :) ");
    }

    /*
    * 멤버 본인 피드 조회 기능
    * 권한 : USER, ADMIN
    * */
    @GetMapping("/feed")
    public ApiResponse<MemberFeedResponseDto> getMyFeedInfo() {

        MemberFeedResponseDto response = memberService.getMyFeedInfo();

        return ApiResponse.ok(response);
    }

    /*
     * 상대 멤버 피드 조회 기능
     * 권한 : USER, ADMIN
     * */
    @GetMapping("/feed/{nickName}")
    public ApiResponse<MemberFeedResponseDto> getMemberFeedInfo(@PathVariable String nickName) {

        MemberFeedResponseDto response = memberService.getMemberFeedInfo(nickName);

        return ApiResponse.ok(response);
    }


    /*
    * 멤버 본인 피드 정보 수정 기능
    * 권한 : USER, ADMIN
    * */
    @PatchMapping("/feed")
    public ApiResponse updateMyFeedInfo(@ModelAttribute MemberEditInfoPatchDto memberEditInfoPatchDto,
                                           @RequestPart(value = "image", required = false) Optional<MultipartFile> image) throws IOException{


        MemberFeedResponseDto response = memberService.updateMyFeedInfo(memberEditInfoPatchDto, image);

        //변경됬으니까 변경된 입력값을 알려줘야 한다.
        return ApiResponse.ok(response);
    }



}
