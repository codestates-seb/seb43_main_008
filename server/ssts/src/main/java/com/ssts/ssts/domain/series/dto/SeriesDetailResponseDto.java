package com.ssts.ssts.domain.series.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeriesDetailResponseDto {



    private Long id;

    private String title;

    private String image;

    private Boolean isBookmarked;


    public static SeriesDetailResponseDto of(Long seriesId, String title, String image,Boolean isBookmarked ) {
        SeriesDetailResponseDto seriesResponseDto = new SeriesDetailResponseDto();

        seriesResponseDto.setId(seriesId);
        seriesResponseDto.setTitle(title);
        seriesResponseDto.setImage(image);
        seriesResponseDto.setIsBookmarked(isBookmarked);


        return seriesResponseDto;
    }
}
