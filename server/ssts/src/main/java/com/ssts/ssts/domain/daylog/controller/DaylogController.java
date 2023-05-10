package com.ssts.ssts.domain.daylog.controller;


import com.ssts.ssts.domain.daylog.dto.DaylogPostDto;
import com.ssts.ssts.domain.daylog.service.DaylogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class DaylogController {


    private DaylogService daylogService;


    @PostMapping("/{series-id}")
    public ResponseEntity createSeries(@PathVariable("series-id") Long seriesId, DaylogPostDto daylogPostDto){



        return null;
    }
}
