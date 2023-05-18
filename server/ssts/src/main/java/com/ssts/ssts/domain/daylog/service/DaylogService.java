package com.ssts.ssts.domain.daylog.service;


import com.ssts.ssts.domain.daylog.dto.DaylogPageResponseDto;
import com.ssts.ssts.domain.daylog.dto.DaylogPostDto;
import com.ssts.ssts.domain.daylog.dto.DaylogResponseDto;
import com.ssts.ssts.domain.daylog.entity.Daylog;
import com.ssts.ssts.domain.daylog.repository.DaylogRepository;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.service.SeriesService;
import com.ssts.ssts.global.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RequiredArgsConstructor
@Service
public class DaylogService {

    private final DaylogRepository daylogRepository;

    private final SeriesService seriesService;

    private final MemberService memberService;

    private final S3Uploader s3ImageUploader;


    @Transactional
    public DaylogResponseDto saveDaylog(Long seriesId, DaylogPostDto daylogPostDto){
        memberService.findMemberByToken();

        Daylog daylog = Daylog.of(daylogPostDto.getContent());
        Series series = seriesService.findVerifiedSeries(seriesId);

        series.setDaylogCount(series.getDaylogCount()+1);
        daylog.addSeries(series);
        daylogRepository.save(daylog);


        return this.DaylogToDaylogResponseDto(daylog);
    }

    @Transactional
    public DaylogResponseDto saveDaylog(Long seriesId, DaylogPostDto daylogPostDto,MultipartFile image) throws IOException {
        memberService.findMemberByToken();
        Daylog daylog = Daylog.of(daylogPostDto.getContent());
        Series series = seriesService.findVerifiedSeries(seriesId);

        if(!image.isEmpty()){
            String saveFileName = s3ImageUploader.upload(image,"daylog");
            daylog.setContentImg(saveFileName);
            series.setImage(saveFileName);
            series.setDaylogCount(series.getDaylogCount()+1);
        }

        daylog.addSeries(series);
        daylogRepository.save(daylog);

        return this.DaylogToDaylogResponseDto(daylog);
    }





    public DaylogPageResponseDto getDaylogList(Long seriesId, int page, int size) {
        memberService.findMemberByToken();
        Page<Daylog> daylogsInfo = daylogRepository.findBySeries_id(seriesId,
                PageRequest.of(page, size, Sort.by("id").descending()));

        List<Daylog> daylogList = daylogsInfo.getContent();

        List<DaylogResponseDto> list = this.seriesToSeriesListResponseDtos(daylogList);

        return new DaylogPageResponseDto(list, daylogsInfo);
        }


    @NotNull
    private DaylogResponseDto DaylogToDaylogResponseDto(Daylog daylog){

        return DaylogResponseDto.of(daylog.getId(),
                daylog.getContent(),
                daylog.getImage(),
                daylog.getCreatedAt(),
                daylog.getSeries());

    }



    private List<DaylogResponseDto> seriesToSeriesListResponseDtos(List<Daylog> daylogList){
        if (daylogList ==null){
            return null;
        }
        List<DaylogResponseDto> list = new ArrayList<>(daylogList.size());
        Iterator iterator = daylogList.iterator();

        while (iterator.hasNext()){
            Daylog daylog = (Daylog) iterator.next();
            list.add(this.DaylogToDaylogResponseDto(daylog));
        }

        return list;
        //클래스 추가 , 책임 이전
    }



    }




