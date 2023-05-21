package com.ssts.ssts.domain.series.dto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class SeriesPostDto {


    @NotBlank
    @Pattern(regexp = "^.{2,13}$",
            message = "최소 두 글자 이상, 12 글자 이하여야해요!")
    private String title;

}
