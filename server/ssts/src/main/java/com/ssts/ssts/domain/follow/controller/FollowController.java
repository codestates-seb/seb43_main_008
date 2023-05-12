package com.ssts.ssts.domain.follow.controller;

import com.ssts.ssts.domain.follow.dto.FollowingPostDto;
import com.ssts.ssts.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {
    private FollowService followService;

    @PostMapping("/following")
    public ResponseEntity following(FollowingPostDto followingPostDto) {


        followService.following(followingPostDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
