package com.ssts.ssts.domain.daylog.dto;

import com.ssts.ssts.domain.member.entity.Member;
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

    private int daylogNumber;

    private LocalDateTime createdAt;

    private Series series;

    private Member member;

    private boolean isMine = false;



    public static DaylogResponseDto of(Long id, String content, String contentImg, int daylogNumber, LocalDateTime createdAt, Series series, Member member){

        DaylogResponseDto daylogResponseDto = new DaylogResponseDto();

        daylogResponseDto.setId(id);
        daylogResponseDto.setContent(content);
        daylogResponseDto.setContentImg(contentImg);
        daylogResponseDto.setDaylogNumber(daylogNumber);
        daylogResponseDto.setCreatedAt(createdAt);
        daylogResponseDto.setSeries(series);
        daylogResponseDto.setMember(member);

        return daylogResponseDto;
    }


}
