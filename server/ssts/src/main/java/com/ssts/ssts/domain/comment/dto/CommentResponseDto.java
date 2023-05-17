package com.ssts.ssts.domain.comment.dto;


import com.ssts.ssts.domain.comment.Entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private String comment;


    public static CommentResponseDto of(Comment comment) {
        CommentResponseDto responseDto = new CommentResponseDto();

        responseDto.setComment(comment.getComment());

        return responseDto;
    }
}
