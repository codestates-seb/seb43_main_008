package com.ssts.ssts.domain.member.controller;

import com.ssts.ssts.domain.member.dto.MemberTestSignUpDto;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.global.auth.utils.SocialType;
import com.ssts.ssts.global.utils.MultipleResponseDto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TestController {

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
                memberTestSignUpDto.getPhone(),
                SocialType.TEST_SSTS);

        return ApiResponse.ok(member);
    }

    /*
     * 테스트용 멤버 회원 삭제 기능
     * 권한 : ADMIN
     * */
    @DeleteMapping("/test/{memberId}")
    public ApiResponse testDeleteMember(@PathVariable Long memberId) {

        memberService.testDeleteMember(memberId);

        return ApiResponse.ok("id="+memberId+"의 멤버 정보가 삭제됐어요.");
    }
}
