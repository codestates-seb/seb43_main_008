package com.ssts.ssts.domain.series.controller;


import com.ssts.ssts.domain.series.dto.SeriesDetailResponseDto;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.MultipleResponseDto.ApiResponse;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesPostDto;
import com.ssts.ssts.domain.series.dto.SeriesResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesUpdateDto;
import com.ssts.ssts.domain.series.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@Validated
public class SeriesController {


    private final SeriesService seriesService;


    @GetMapping("/feed/series/{nick-name}")
    public ApiResponse getSeriesList(@PathVariable("nick-name") String nickname,
                                     @Positive @RequestParam(value = "page", defaultValue = "1") int page,
                                     @Positive @RequestParam(value = "size", defaultValue = "12") int size){

        PageResponseDto response = seriesService.getSeriesList(nickname,page-1, size);

        return ApiResponse.ok(response);
    }

    @GetMapping("/series")
    public ApiResponse<PageResponseDto> getMainSeriesList(@RequestParam(value = "sort", defaultValue = "newest") String sort,
                                                          @Positive @RequestParam(value = "page", defaultValue = "1") int page,
                                                          @Positive @RequestParam(value = "size", defaultValue = "12") int size){

        if("votes".equals(sort)){
            PageResponseDto response = seriesService.getMainSeriesListByVotes(page-1, size);
            return ApiResponse.ok(response);
        }
        PageResponseDto response = seriesService.getMainSeriesListByNewest(page-1, size);
        return ApiResponse.ok(response);
    }

    @GetMapping("/series/{series-id}")
    public ApiResponse getSeries(@Positive @PathVariable("series-id") Long id){

        SeriesDetailResponseDto response = seriesService.getSeries(id);

        return ApiResponse.ok(response);
    }


    @PostMapping("/series")
    public ApiResponse createSeries(@RequestParam(value = "public", defaultValue = "false") String isPublic,
                                    @Validated @RequestBody SeriesPostDto seriesPostDto){

        SeriesResponseDto response = seriesService.saveSeries(isPublic, seriesPostDto);

        return ApiResponse.create(response);
    }


    @PatchMapping("/series/{series-id}")
    public ApiResponse updateSeries(@Positive @PathVariable("series-id") Long seriesId,
                                    @Validated @RequestBody SeriesUpdateDto seriesUpdateDto){

        SeriesResponseDto response = seriesService.updateSeries(seriesId,seriesUpdateDto);

        return ApiResponse.ok(response);
    }


    @DeleteMapping("/series/{series-id}")
    public ApiResponse deleteSeries(@Positive @PathVariable("series-id") Long id){

        seriesService.deleteSeries(id);

        return ApiResponse.ok();
    }

}
