package com.ssts.ssts.domain.badges.controller;

import com.ssts.ssts.domain.badges.response.BadgeResponse;
import com.ssts.ssts.domain.badges.dto.BadgePostDto;
import com.ssts.ssts.domain.badges.entity.Badge;

import com.ssts.ssts.domain.badges.service.BadgeService;

import com.ssts.ssts.global.utils.MultipleResponseDto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("members/badge")
public class BadgeController {

    private final BadgeService badgeService;

    //test API로 사용하지 않습니다
    @PostMapping
    public ApiResponse postBadge(@RequestBody BadgePostDto postDto){
        Badge response = badgeService.saveBadge(postDto);
        log.info("뺏지. 맹들기.");
        return ApiResponse.create(response);
    }


    //사용자 뱃지 취득하기
    @PostMapping("/{badge_id}")
    public ApiResponse postBadgeMember(@PathVariable("badge_id") Long badgeId){
        badgeService.saveBadgeMember(badgeId);
        return ApiResponse.ok();
    }


//    특정 뱃지 보기, (특정 뱃지 보는 거 상세내용)
//    특정 뱃지를 볼 떄에 isAcquired에 따라서 response가 바뀜
    @GetMapping("/{badge_id}")
    public ApiResponse<BadgeResponse> getBadge(@PathVariable("badge_id") Long badgeId){

        BadgeResponse response =badgeService.findBadge(badgeId);


        return ApiResponse.ok(response);
    }


    //전체 뱃지 조회
    @GetMapping
    public ApiResponse<List<BadgeResponse>> getBadges(){
        List<BadgeResponse> response = badgeService.findAllBadge();

        return ApiResponse.ok(response);
    }



}
