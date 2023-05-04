package com.ssts.ssts.domain.member.controller;


import com.ssts.ssts.domain.member.dto.MemberLoginPostDto;
import com.ssts.ssts.domain.member.dto.MemberSignUpPostDto;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.utils.UriCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/members";
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public ResponseEntity createMember(@RequestBody MemberSignUpPostDto memberSignUpPostDto) {


        Member member = memberService.saveMember(memberSignUpPostDto);
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, member.getId());

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/login")
    public ResponseEntity loginMember(@RequestBody MemberLoginPostDto memberLoginPostDto) {

        //Member member = memberService.findMember(memberLoginPostDto);

        return ResponseEntity.ok().build();
    }
}
