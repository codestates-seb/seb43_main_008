package com.ssts.ssts.domain.daylog.service;



import com.ssts.ssts.domain.daylog.dto.DaylogPostDto;
import com.ssts.ssts.domain.daylog.dto.DaylogResponseDto;
import com.ssts.ssts.domain.daylog.entity.Daylog;
import com.ssts.ssts.domain.daylog.repository.DaylogRepository;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.service.SeriesService;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import com.ssts.ssts.global.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.BindException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
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
        Member member = memberService.findMemberByToken();
        Daylog daylog = Daylog.of(daylogPostDto.getContent());
        Series series = seriesService.findVerifiedSeries(seriesId);

        if (member.getId() != series.getMember().getId()){
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }

        if(!image.isEmpty()){
            String saveFileName = s3ImageUploader.upload(image,"daylog");
            daylog.setContentImg(saveFileName);
            series.setImage(saveFileName);
        }
        series.setDaylogCount(series.getDaylogCount()+1);
        daylog.setDaylogNumber(series.getDaylogCount());

        daylog.addSeries(series);
        daylog.addMember(member);

        daylogRepository.save(daylog);

        return this.DaylogToDaylogResponseDto(daylog);
    }





    public PageResponseDto getDaylogList(Long seriesId, int page, int size) {


        Member member = memberService.findMemberByToken();
        Page<Daylog> daylogsInfo = daylogRepository.findBySeries_id(seriesId,
                PageRequest.of(page, size, Sort.by("id").descending()));

        List<Daylog> daylogList = daylogsInfo.getContent();

        List<DaylogResponseDto> list = this.seriesToSeriesListResponseDtos(daylogList);
        List<DaylogResponseDto> responseDtos = this.isMineDaylog(list, member);
        log.info("responseDtos={}", responseDtos);
        return new PageResponseDto<>(responseDtos, daylogsInfo);
        }


    @NotNull
    private DaylogResponseDto DaylogToDaylogResponseDto(Daylog daylog){

        return DaylogResponseDto.of(daylog.getId(),
                daylog.getContent(),
                daylog.getImage(),
                daylog.getDaylogNumber(),
                daylog.getCreatedAt(),
                daylog.getSeries(),
                daylog.getMember());


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

    private List<DaylogResponseDto> isMineDaylog(List<DaylogResponseDto> daylogResponseDtosList, Member member){

        for(DaylogResponseDto daylogResponseDto : daylogResponseDtosList){
            if(daylogResponseDto.getMember().getId() == member.getId()){
                daylogResponseDto.setMine(true);
            }
        }

        return daylogResponseDtosList;
    }

    }




