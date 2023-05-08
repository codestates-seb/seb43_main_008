package com.ssts.ssts.domain.series.service;


import com.ssts.ssts.domain.series.dto.SeriesPostDto;
import com.ssts.ssts.domain.series.dto.SeriesResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesUpdateDto;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;



    public SeriesResponseDto getSeries(Long id){

        Series series = this.findVerifiedSeries(id);

        return SeriesResponseDto.of(series.getId(),
                series.getTitle(),
                series.getDaylogCount(),
                series.getCreatedAt(),
                series.getModifiedAt(),
                series.getVoteCount(),
                series.getVoteResult(),
                series.getVoteAgree(),
                series.getVoteDisagree(),
                series.getRevoteResult(),
                series.getRevoteAgree(),
                series.getRevoteDisagree(),
                series.getSeriesStatus(),
                series.getIsPublic(),
                series.getIsEditable(),
                series.getIsActive());

    }


    public SeriesResponseDto saveSeries(SeriesPostDto seriesPostDto){

        Series series = Series.of(seriesPostDto.getTitle());

        seriesRepository.save(series);

        return SeriesResponseDto.of(series.getId(),
                series.getTitle(),
                series.getDaylogCount(),
                series.getCreatedAt(),
                series.getModifiedAt(),
                series.getVoteCount(),
                series.getVoteResult(),
                series.getVoteAgree(),
                series.getVoteDisagree(),
                series.getRevoteResult(),
                series.getRevoteAgree(),
                series.getRevoteDisagree(),
                series.getSeriesStatus(),
                series.getIsPublic(),
                series.getIsEditable(),
                series.getIsActive());
    }


    public SeriesResponseDto updateSeries(Long id, SeriesUpdateDto seriesUpdateDto){

        Series series = this.findVerifiedSeries(id);


        return SeriesResponseDto.of(series.getId(),
                series.getTitle(),
                series.getDaylogCount(),
                series.getCreatedAt(),
                series.getModifiedAt(),
                series.getVoteCount(),
                series.getVoteResult(),
                series.getVoteAgree(),
                series.getVoteDisagree(),
                series.getRevoteResult(),
                series.getRevoteAgree(),
                series.getRevoteDisagree(),
                series.getSeriesStatus(),
                series.getIsPublic(),
                series.getIsEditable(),
                series.getIsActive());

    }

    public void deleteSeries(Long id){

        Series series = this.findVerifiedSeries(id);
        seriesRepository.delete(series);
    }





    public Series findVerifiedSeries(Long questionId){
        Optional<Series> optionalQuestion = seriesRepository.findById(questionId);

        Series findSeries =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        return findSeries;

    }

}
