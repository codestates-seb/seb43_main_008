package com.ssts.ssts.domain.daylog.controller;


import com.ssts.ssts.domain.daylog.dto.DaylogPageResponseDto;
import com.ssts.ssts.domain.daylog.dto.DaylogPostDto;
import com.ssts.ssts.domain.daylog.dto.DaylogResponseDto;
import com.ssts.ssts.domain.daylog.service.DaylogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class DaylogController {


    private final DaylogService daylogService;


    @PostMapping("/{series-id}/daylog")
    public ResponseEntity createSeries(@PathVariable("series-id") Long seriesId, @RequestBody DaylogPostDto daylogPostDto){

        DaylogResponseDto responseDto = daylogService.saveDaylog(seriesId, daylogPostDto);

        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{series-id}/daylog")
    public ResponseEntity getDaylogList(@PathVariable("series-id") Long id,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "7") int size){

        DaylogPageResponseDto response = daylogService.getDaylogList(id, page-1, size);

        return ResponseEntity.ok(response);
    }
}
