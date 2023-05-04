package com.ssts.ssts.domain.series.controller;


import com.ssts.ssts.domain.series.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SeriesController {


    private final SeriesService seriesService;


}
