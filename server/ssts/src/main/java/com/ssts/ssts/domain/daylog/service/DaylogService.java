package com.ssts.ssts.domain.daylog.service;

import com.ssts.ssts.domain.daylog.dto.DaylogPostDto;
import com.ssts.ssts.domain.daylog.dto.DaylogResponseDto;
import com.ssts.ssts.domain.daylog.entity.Daylog;
import com.ssts.ssts.domain.daylog.repository.DaylogRepository;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DaylogService {

    private final DaylogRepository daylogRepository;

    private final SeriesRepository seriesRepository;


    public DaylogResponseDto saveDaylog(Long seriesId, DaylogPostDto daylogPostDto){

        Daylog daylog = Daylog.of(daylogPostDto.getContent(), daylogPostDto.getDaylogImg());

        Optional<Series> optionalQuestion = seriesRepository.findById(seriesId);

        Series findSeries =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        daylog.addSeries(findSeries);
        daylogRepository.save(daylog);

        DaylogResponseDto daylogResponseDto = DaylogResponseDto.of(daylog.getId(), daylog.getContent(), daylog.getContentimg(), daylog.getCreatedAt(), daylog.getSeries());

        return daylogResponseDto;
    }

}
