package com.ssts.ssts.domain.series.controller;


import com.ssts.ssts.domain.series.dto.SeriesPostDto;
import com.ssts.ssts.domain.series.dto.SeriesResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesUpdateDto;
import com.ssts.ssts.domain.series.service.SeriesService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class SeriesController {


    private final SeriesService seriesService;



    @GetMapping("/{series-id}")
    public ResponseEntity getSeries(@PathVariable("series-id") Long id){

        SeriesResponseDto response = seriesService.getSeries(id);

        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity createSeries(@RequestBody SeriesPostDto seriesPostDto){

        SeriesResponseDto response = seriesService.saveSeries(1L,seriesPostDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

//    @PostMapping("/{member-id}")
//    public ResponseEntity createSeries(@RequestBody SeriesPostDto seriesPostDto, @PathVariable Long memberId){
//
//        SeriesResponseDto response = seriesService.saveSeries(memberId,seriesPostDto);
//
//        return new ResponseEntity(response, HttpStatus.CREATED);
//    }

    @PatchMapping("/{series-id}")
    public ResponseEntity updateSeries(@PathVariable("series-id") Long id, @RequestBody
    SeriesUpdateDto seriesUpdateDto){

        SeriesResponseDto response = seriesService.updateSeries(id, seriesUpdateDto);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{series-id}")
    public ResponseEntity deleteSeries(@PathVariable("series-id") Long id){

        seriesService.deleteSeries(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
