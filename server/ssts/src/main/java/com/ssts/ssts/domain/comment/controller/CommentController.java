package com.ssts.ssts.domain.comment.controller;


import com.ssts.ssts.domain.comment.dto.CommentPostDto;
import com.ssts.ssts.domain.comment.dto.CommentResponseDto;
import com.ssts.ssts.domain.comment.dto.CommentUpdateDto;
import com.ssts.ssts.domain.comment.service.CommentService;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{series-id}")
    public ResponseEntity getCommentList(@PathVariable("series-id") Long seriesId,
                                         @AuthenticationPrincipal String authId, @RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "12") int size){


        PageResponseDto response = commentService.getCommentList(seriesId,page, size);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{series-id}")
    public ResponseEntity createComment(@PathVariable("series-id") Long seriesId, @RequestBody CommentPostDto commentPostDto){

        CommentResponseDto response = commentService.saveComment(seriesId, commentPostDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{series-id}/{comment-id}")
    public ResponseEntity updateComment(@PathVariable("series-id") Long seriesId,
                                        @PathVariable("comment-id") Long commentId,
                                        @RequestBody CommentUpdateDto commentUpdateDto){

        CommentResponseDto response = commentService.updateDto(seriesId, commentId, commentUpdateDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{series-id}/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("series-id") Long seriesId,
                                        @PathVariable("comment-id") Long commentId){

        commentService.deleteComment(commentId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
