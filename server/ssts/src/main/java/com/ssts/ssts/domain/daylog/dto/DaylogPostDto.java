package com.ssts.ssts.domain.daylog.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DaylogPostDto {

    private String content;

    private MultipartFile daylogImg;

}
