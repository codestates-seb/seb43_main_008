package com.ssts.ssts.domain.daylog.service;


import com.ssts.ssts.domain.daylog.dto.DaylogPageResponseDto;
import com.ssts.ssts.domain.daylog.dto.DaylogPostDto;
import com.ssts.ssts.domain.daylog.dto.DaylogResponseDto;
import com.ssts.ssts.domain.daylog.entity.Daylog;
import com.ssts.ssts.domain.daylog.repository.DaylogRepository;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import com.ssts.ssts.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DaylogService {

    private final DaylogRepository daylogRepository;

    private final SeriesRepository seriesRepository;

    private final MemberService memberService;

    private final S3Uploader s3ImageUploader;



    public DaylogResponseDto saveDaylog(Long seriesId, DaylogPostDto daylogPostDto){
        memberService.findMemberByToken();

        Daylog daylog = Daylog.of(daylogPostDto.getContent());

        Optional<Series> optionalQuestion = seriesRepository.findById(seriesId);

        Series findSeries =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        daylog.addSeries(findSeries);
        daylogRepository.save(daylog);


        return this.DaylogToDaylogResponseDto(daylog);
    }

    public DaylogResponseDto saveDaylog(Long seriesId, DaylogPostDto daylogPostDto,MultipartFile image) throws IOException {
        memberService.findMemberByToken();
        Daylog daylog = Daylog.of(daylogPostDto.getContent());

        Optional<Series> optionalQuestion = seriesRepository.findById(seriesId);

        Series findSeries =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        if(!image.isEmpty()){
            String saveFileName = s3ImageUploader.upload(image,"daylog");
            daylog.setContentImg(saveFileName);
            findSeries.setImage(saveFileName);
        }

        daylog.addSeries(findSeries);
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
    }



    }




