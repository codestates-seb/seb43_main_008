package com.ssts.ssts.domain.follow.controller;

import com.ssts.ssts.domain.follow.dto.OpponentNickNameDto;
import com.ssts.ssts.domain.follow.service.FollowService;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;


    @PostMapping("/follow/{nickName}")
    public ResponseEntity following(@PathVariable String nickName) {
        followService.following(nickName);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/unfollow/{nickName}")
    public ResponseEntity unfollowing(@PathVariable String nickName){

        followService.unfollowing(nickName);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/follow/followings")
    public ResponseEntity followingList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "12") int size){

        PageResponseDto response=followService.followingList(page-1, size);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/follow/followers")
    public ResponseEntity followerList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "12") int size){

        PageResponseDto response=followService.followerList(page-1,size);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
