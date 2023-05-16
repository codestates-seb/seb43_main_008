package com.ssts.ssts.domain.comment.controller;


import com.ssts.ssts.domain.comment.service.CommentService;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{series-id}")
    public ResponseEntity getCommentList(@AuthenticationPrincipal String authId, @RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "12") int size){

        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();

        log.info("authen2={}", authentication2);
        log.info("prin={}",authentication);
        log.info("authId= {}", authId);
        PageResponseDto response = commentService.getCommentList(page, size);

        return null;
    }
}
