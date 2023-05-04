package com.ssts.ssts.domain.series.mapper;

import com.ssts.ssts.domain.series.dto.SeriesPostDto;
import com.ssts.ssts.domain.series.dto.SeriesResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesUpdateDto;
import com.ssts.ssts.domain.series.entity.Series;

public class SeriesMapper {


    public Series postDtoToSeries(SeriesPostDto seriesPostDto) {
        if (seriesPostDto == null) {
            return null;
        }

        return Series.builder()
                .title(seriesPostDto.getTitle())
                .build();
    }


    public Series updateDtoToSeries(SeriesUpdateDto seriesUpdateDto){
        if (seriesUpdateDto == null) {
            return null;
        }

        return Series.builder()
                .title(seriesUpdateDto.getTitle())
                .build();
    }


    public SeriesResponseDto SeriesToResponseDto(Series series){
        if(series == null){
            return null;
        }

        return SeriesResponseDto.builder()
                .id(series.getId())
                .title(series.getTitle())
                .createdAt(series.getCreatedAt())
                .modifiedAt(series.getModifiedAt())
                .voteCount(series.getVoteCount())
                .revoteResult(series.getRevoteResult())
                .voteAgree(series.getVoteAgree())
                .voteDisagree(series.getVoteDisagree())
                .revoteResult(series.getRevoteResult())
                .revoteAgree(series.getRevoteAgree())
                .revoteDisagree(series.getRevoteDisagree())
                .seriesStatus(series.getSeriesStatus())
                .isPublic(series.getIsPublic())
                .isEditable(series.getIsEditable())
                .isActive(series.getIsActive())
                .build();
    }
}
