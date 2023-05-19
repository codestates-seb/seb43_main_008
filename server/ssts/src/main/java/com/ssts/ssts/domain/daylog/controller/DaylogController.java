package com.ssts.ssts.domain.daylog.controller;


import com.ssts.ssts.domain.daylog.dto.DaylogPageResponseDto;
import com.ssts.ssts.domain.daylog.dto.DaylogPostDto;
import com.ssts.ssts.domain.daylog.dto.DaylogResponseDto;
import com.ssts.ssts.domain.daylog.service.DaylogService;
import com.ssts.ssts.global.utils.MultipleResponseDto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class DaylogController {


    private final DaylogService daylogService;


    @PostMapping("/{series-id}/daylog")
    public ApiResponse createSeries(@PathVariable("series-id") Long seriesId,
                                    @ModelAttribute DaylogPostDto daylogPostDto,
                                    @RequestPart(value = "image") MultipartFile image) throws IOException {

        DaylogResponseDto responseDto = daylogService.saveDaylog(seriesId, daylogPostDto, image);

        return ApiResponse.create(responseDto)
    }


    @GetMapping("/{series-id}/daylog")
    public ApiResponse getDaylogList(@PathVariable("series-id") Long id,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "7") int size){

        DaylogPageResponseDto response = daylogService.getDaylogList(id, page-1, size);

        return ApiResponse.ok(response);
    }
}
