package com.ssts.ssts.domain.series.service;


import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.domain.series.dto.SeriesPageResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesPostDto;
import com.ssts.ssts.domain.series.dto.SeriesResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesUpdateDto;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.S3Uploader;
import com.ssts.ssts.global.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeriesService {

    private final SeriesRepository seriesRepository;

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final UpdateUtils<Series> updateUtils;

    private final S3Uploader s3Uploader;



    public SeriesPageResponseDto getSeriesList(int page, int size){

        Member findMember = memberService.findMemberByToken();

        Page<Series> seriesInfo = seriesRepository.findByMember_id(findMember.getId(), PageRequest.of(page, size,
                Sort.by("id").descending()));

        List<Series> seriesList = seriesInfo.getContent();
        List<SeriesResponseDto> list = this.seriesToSeriesListResponseDtos(seriesList);

        return new SeriesPageResponseDto(list, seriesInfo);
    }

    public SeriesPageResponseDto getMainSeriesList(int page, int size){

        Page<Series> seriesInfo = seriesRepository.findAll(PageRequest.of(page, size,
                Sort.by("id").descending()));

        List<Series> seriesList = seriesInfo.getContent();
        List<SeriesResponseDto> list = this.seriesToSeriesListResponseDtos(seriesList);

        return new SeriesPageResponseDto(list, seriesInfo);
    }

    public SeriesResponseDto getSeries(Long id){
        memberService.findMemberByToken();
        Series series = this.findVerifiedSeries(id);

        return this.seriesToSeriesResponseDto(series);
    }




    public SeriesResponseDto saveSeries(SeriesPostDto seriesPostDto){

        Member authMember = memberService.findMemberByToken();
        Series series = Series.of(seriesPostDto.getTitle());
        Member member = memberService.findMemberById(authMember.getId());

        if(authMember.getId()!=member.getId()){
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }

        series.setImage(s3Uploader.getS3("ssts-img", "series/series-image.png"));
        series.addMember(member);
        seriesRepository.save(series);

        return this.seriesToSeriesResponseDto(series);
    }


    public SeriesResponseDto updateSeries(SeriesUpdateDto seriesUpdateDto){
        Member member = memberService.findMemberByToken();

        Series DescSeries = this.findVerifiedSeries(member.getId());
        Series series = Series.of(seriesUpdateDto.getTitle());

        if(DescSeries.getMember().getId()!= member.getId()){
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }

        Series updateSeries = updateUtils.copyNonNullProperties(series,DescSeries);
        seriesRepository.save(updateSeries);

        return this.seriesToSeriesResponseDto(updateSeries);

    }

    public void deleteSeries(Long id){
        Member member = memberService.findMemberByToken();
        Series series = this.findVerifiedSeries(id);
        if(series.getMember().getId()!=member.getId()){
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }

        seriesRepository.delete(series);
    }





    public Series findVerifiedSeries(Long seriesId){
        Optional<Series> optionalQuestion = seriesRepository.findById(seriesId);

        Series findSeries =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        return findSeries;

    }

    @NotNull
    private SeriesResponseDto seriesToSeriesResponseDto(Series series) {


        return SeriesResponseDto.of(series.getId(),
                series.getTitle(),
                series.getImage(),
                series.getDaylogCount(),
                series.getCreatedAt(),
                series.getModifiedAt(),
                series.getVoteCount(),
                series.getVoteResult(),
                series.getVoteAgree(),
                series.getVoteDisagree(),
                series.getRevoteResult(),
                series.getRevoteAgree(),
                series.getRevoteDisagree(),
                series.getVoteStatus(),
                series.getIsPublic(),
                series.getIsEditable(),
                series.getIsActive());
    }

    public List<SeriesResponseDto> seriesToSeriesListResponseDtos(List<Series> seriesList){
        if (seriesList ==null){
            return null;
        }
        List<SeriesResponseDto> list = new ArrayList<>(seriesList.size());
        Iterator iterator = seriesList.iterator();

        while (iterator.hasNext()){
            Series series = (Series) iterator.next();
            list.add(this.seriesToSeriesResponseDto(series));
        }

        return list;
    }


}
