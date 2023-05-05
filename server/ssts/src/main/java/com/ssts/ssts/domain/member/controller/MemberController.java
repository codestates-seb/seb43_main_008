package com.ssts.ssts.domain.member.controller;


import com.ssts.ssts.domain.member.dto.MemberEditInfoPatchDto;
import com.ssts.ssts.domain.member.dto.MemberEditInfoResponseDto;
import com.ssts.ssts.domain.member.dto.MemberMyPageResponseDto;
import com.ssts.ssts.domain.member.dto.MemberSignUpPostDto;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/members";
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
    * 멤버 회원가입 기능
    *
    * */
    @PostMapping("/signup")
    public ResponseEntity createMember(@RequestBody MemberSignUpPostDto memberSignUpPostDto) {


        Member member = memberService.saveMember(memberSignUpPostDto);
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, member.getId());

        return ResponseEntity.created(location).build();
    }

    /*
    * 멤버 조회 기능
    *
    * */
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberMyPageResponseDto> findMember(@PathVariable long memberId) {

        MemberMyPageResponseDto response = memberService.findMember(memberId);

        return ResponseEntity.ok(response);
    }

    /*
     * 멤버 탈퇴 기능
     *
     * */
    @DeleteMapping("/{memberId}")
    public ResponseEntity withdrawMember(@PathVariable long memberId) {

        Member member=memberService.changeMemberStatusWithdraw(memberId);

        //로그 출력 : 어떤 아이디가 탈퇴했다
        return ResponseEntity.status(HttpStatus.OK).body("You have been successfully withdrawn.");
    }

    /*
    * 멤버 정보 수정
    *
    * */
    @PatchMapping("/edit/{memberId}")
    public ResponseEntity<MemberEditInfoResponseDto> updateMemberInfo(@PathVariable long memberId, @RequestBody MemberEditInfoPatchDto memberEditInfoPatchDto) {

        MemberEditInfoResponseDto response = memberService.editMemberInfo(memberId, memberEditInfoPatchDto);
        // 요청 body값이 nickname은 반드시 들어가야한다,image랑 introduce는 nullable이라서 선택적.
        //

        //로그 출력 : 어떤 아이디가 수정했다
        return ResponseEntity.ok(response);
    }

}
