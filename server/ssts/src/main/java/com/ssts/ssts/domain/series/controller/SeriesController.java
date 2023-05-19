package com.ssts.ssts.domain.series.controller;


import com.ssts.ssts.global.utils.MultipleResponseDto.ApiResponse;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesPostDto;
import com.ssts.ssts.domain.series.dto.SeriesResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesUpdateDto;
import com.ssts.ssts.domain.series.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
public class SeriesController {


    private final SeriesService seriesService;


    @GetMapping("/feed/series/{member-id}")
    public ApiResponse getSeriesList(@PathVariable("member-id") Long memberid,@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "12") int size){

        PageResponseDto response = seriesService.getSeriesList(memberid,page-1, size);

        return ApiResponse.ok(response);
    }

    @GetMapping("/series")
    public ApiResponse<PageResponseDto> getMainSeriesList(@RequestParam(value = "sort", defaultValue = "newest") String sort, @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "12") int size){
        if("votes".equals(sort)){
            PageResponseDto response = seriesService.getMainSeriesListByVotes(page-1, size);
            return ApiResponse.ok(response);
        }
        PageResponseDto response = seriesService.getMainSeriesListByNewest(page-1, size);
        return ApiResponse.ok(response);
    }

    @GetMapping("/series/{series-id}")
    public ApiResponse getSeries(@PathVariable("series-id") Long id){

        SeriesResponseDto response = seriesService.getSeries(id);

        return ApiResponse.ok(response);
    }


    @PostMapping("/series")
    public ApiResponse createSeries(@RequestParam(value = "public", defaultValue = "false") String isPublic, @RequestBody SeriesPostDto seriesPostDto){

        SeriesResponseDto response = seriesService.saveSeries(isPublic, seriesPostDto);

        return ApiResponse.ok(response);
    }


    @PatchMapping("/series/{series-id}")
    public ResponseEntity updateSeries(@PathVariable("series-id") Long seriesId, @RequestBody
    SeriesUpdateDto seriesUpdateDto){

        SeriesResponseDto response = seriesService.updateSeries(seriesId,seriesUpdateDto);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/series/{series-id}")
    public ResponseEntity deleteSeries(@PathVariable("series-id") Long id){

        seriesService.deleteSeries(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
