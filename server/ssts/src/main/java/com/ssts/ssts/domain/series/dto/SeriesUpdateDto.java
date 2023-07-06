package com.ssts.ssts.domain.series.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class SeriesUpdateDto {

    @Pattern(regexp = "^.{2,13}$",
            message = "최소 두 글자 이상, 12 글자 이하여야해요!")
    private String title;
    private Boolean isPublic;

}
