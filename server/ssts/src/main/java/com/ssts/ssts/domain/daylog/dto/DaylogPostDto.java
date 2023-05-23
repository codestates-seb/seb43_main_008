package com.ssts.ssts.domain.daylog.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class DaylogPostDto {

    @Pattern(regexp = "^.{2,}$",
            message = "최소 두 글자 이상 작성해야 합니다.")
    private String content;

    private MultipartFile image;

}
