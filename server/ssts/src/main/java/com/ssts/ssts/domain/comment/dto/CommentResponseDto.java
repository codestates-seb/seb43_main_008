package com.ssts.ssts.domain.comment.dto;


import com.ssts.ssts.domain.comment.Entity.Comment;
import com.ssts.ssts.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;
    private String comment;
    private Member member;


    public static CommentResponseDto of(Comment comment) {
        CommentResponseDto responseDto = new CommentResponseDto();

        responseDto.setId(comment.getId());
        responseDto.setComment(comment.getComment());
        responseDto.setMember(comment.getMember());

        return responseDto;
    }
}
