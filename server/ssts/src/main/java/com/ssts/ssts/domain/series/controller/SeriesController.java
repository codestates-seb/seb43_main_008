package com.ssts.ssts.domain.series.controller;


import com.ssts.ssts.domain.series.dto.SeriesPageResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesPostDto;
import com.ssts.ssts.domain.series.dto.SeriesResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesUpdateDto;
import com.ssts.ssts.domain.series.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class SeriesController {


    private final SeriesService seriesService;



    @GetMapping("members/{member-id}")
    public ResponseEntity getSeriesList(@AuthenticationPrincipal String authId, @PathVariable("member-id") Long id,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "12") int size){

        SeriesPageResponseDto response = seriesService.getSeriesList(id, page-1, size);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{series-id}")
    public ResponseEntity getSeries(@PathVariable("series-id") Long id){

        SeriesResponseDto response = seriesService.getSeries(id);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/{member-id}")  //추후 member-id url삭제
    public ResponseEntity createSeries(@PathVariable("member-id") Long memberid, @RequestBody SeriesPostDto seriesPostDto){

        SeriesResponseDto response = seriesService.saveSeries(memberid, seriesPostDto);

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

        SeriesResponseDto response = seriesService.updateSeries(1L, id, seriesUpdateDto);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{series-id}")
    public ResponseEntity deleteSeries(@PathVariable("series-id") Long id){

        seriesService.deleteSeries(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
