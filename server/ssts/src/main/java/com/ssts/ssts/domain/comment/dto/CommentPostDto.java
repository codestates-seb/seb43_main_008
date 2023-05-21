package com.ssts.ssts.domain.comment.dto;


import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class CommentPostDto {

    @Pattern(regexp = "^.{2,}$",
            message = "최소 두 글자 이상 작성해야 합니다.")
    private String comment;

}
