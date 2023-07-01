package com.ssts.ssts.domain.comment.controller;


import com.ssts.ssts.domain.comment.dto.CommentPostDto;
import com.ssts.ssts.domain.comment.dto.CommentResponseDto;
import com.ssts.ssts.domain.comment.dto.CommentUpdateDto;
import com.ssts.ssts.domain.comment.service.CommentService;
import com.ssts.ssts.global.utils.MultipleResponseDto.ApiResponse;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CommentController {

//    private final CommentService commentService;

//    @GetMapping("/{series-id}")
//    public ApiResponse getCommentList(@Positive @PathVariable("series-id") Long seriesId,
//                                      @Positive @RequestParam(value = "page", defaultValue = "1") int page,
//                                      @Positive @RequestParam(value = "size", defaultValue = "12") int size){
//
//
//        PageResponseDto response = commentService.getCommentList(seriesId,page-1, size);
//
//        return ApiResponse.ok(response);
//    }
//
//    @PostMapping("/{series-id}")
//    public ApiResponse createComment(@Positive @PathVariable("series-id") Long seriesId, @RequestBody CommentPostDto commentPostDto){
//
//        CommentResponseDto response = commentService.saveComment(seriesId, commentPostDto);
//
//        return ApiResponse.create(response);
//    }
//
//    @PatchMapping("/{series-id}/{comment-id}")
//    public ApiResponse updateComment(@Positive @PathVariable("series-id") Long seriesId,
//                                     @Positive @PathVariable("comment-id") Long commentId,
//                                     @RequestBody CommentUpdateDto commentUpdateDto){
//
//        CommentResponseDto response = commentService.updateComment(seriesId, commentId, commentUpdateDto);
//
//        return ApiResponse.ok(response);
//    }
//
//    @DeleteMapping("/{series-id}/{comment-id}")
//    public ApiResponse deleteComment(@Positive @PathVariable("series-id") Long seriesId,
//                                     @Positive @PathVariable("comment-id") Long commentId){
//
//        commentService.deleteComment(commentId);
//
//        return ApiResponse.ok();
//    }
}
