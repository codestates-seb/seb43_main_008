package com.ssts.ssts.domain.series.controller;


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
@RequestMapping("/series")
@RequiredArgsConstructor
public class SeriesController {


    private final SeriesService seriesService;


    @GetMapping("/members/{member-id}")
    public ResponseEntity getSeriesList(@PathVariable("member-id") Long memberid,@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "12") int size){

        PageResponseDto response = seriesService.getSeriesList(memberid,page-1, size);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity getMainSeriesList(@RequestParam(value = "sort", defaultValue = "newest") String sort, @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "12") int size){
        if("votes".equals(sort)){
            PageResponseDto response = seriesService.getMainSeriesListByVotes(page-1, size);
            return ResponseEntity.ok(response);
        }
        PageResponseDto response = seriesService.getMainSeriesListByNewest(page-1, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{series-id}")
    public ResponseEntity getSeries(@PathVariable("series-id") Long id){

        SeriesResponseDto response = seriesService.getSeries(id);

        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity createSeries(@RequestParam(value = "public", defaultValue = "false") String isPublic, @RequestBody SeriesPostDto seriesPostDto){

        SeriesResponseDto response = seriesService.saveSeries(isPublic, seriesPostDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @PatchMapping("/{series-id}")
    public ResponseEntity updateSeries(@PathVariable("series-id") Long seriesId, @RequestBody
    SeriesUpdateDto seriesUpdateDto){

        SeriesResponseDto response = seriesService.updateSeries(seriesId,seriesUpdateDto);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{series-id}")
    public ResponseEntity deleteSeries(@PathVariable("series-id") Long id){

        seriesService.deleteSeries(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
