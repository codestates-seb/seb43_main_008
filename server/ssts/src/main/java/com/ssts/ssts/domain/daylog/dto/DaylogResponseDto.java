package com.ssts.ssts.domain.daylog.dto;

import com.ssts.ssts.domain.series.entity.Series;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class DaylogResponseDto {

    private Long id;

    private String content;

    private String contentImg;

    private LocalDateTime createdAt;

    private Series series;


    public static DaylogResponseDto of(Long id, String content, String contentImg, LocalDateTime createdAt, Series series){

        DaylogResponseDto daylogResponseDto = new DaylogResponseDto();

        daylogResponseDto.setId(id);
        daylogResponseDto.setContent(content);
        daylogResponseDto.setContentImg(contentImg);
        daylogResponseDto.setCreatedAt(createdAt);
        daylogResponseDto.setSeries(series);

        return daylogResponseDto;
    }
}
