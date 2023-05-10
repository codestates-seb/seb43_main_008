package com.ssts.ssts.domain.series.service;


import com.ssts.ssts.domain.daylog.entity.Daylog;
import com.ssts.ssts.domain.daylog.repository.DaylogRepository;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.domain.series.dto.SeriesPostDto;
import com.ssts.ssts.domain.series.dto.SeriesResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesUpdateDto;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import com.ssts.ssts.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    private final MemberRepository memberRepository;

    private final DaylogRepository daylogRepository;
    private final UpdateUtils<Series> updateUtils;



    public SeriesResponseDto getSeries(Long id, int page, int size){

        Series series = this.findVerifiedSeries(id);

        Page<Daylog> daylogInfo = seriesRepository.findAllDaylogsById(id, PageRequest.of(page, size,
                Sort.by("daylogId").descending()));

        List<Daylog> daylogs = daylogInfo.getContent();

        SeriesResponseDto responseDto = SeriesResponseDto.of(series.getId(),
                series.getTitle(),
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
                series.getIsActive(),
                series.getMember());

        return responseDto;
    }


    public SeriesResponseDto saveSeries(Long memberId, SeriesPostDto seriesPostDto){

        Series series = Series.of(seriesPostDto.getTitle());
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        series.addMember(findMember);
        seriesRepository.save(series);

        return SeriesResponseDto.of(series.getId(),
                series.getTitle(),
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
                series.getIsActive(),
                series.getMember());
    }


    public SeriesResponseDto updateSeries(Long memeberId, Long id, SeriesUpdateDto seriesUpdateDto){

        Series DescSeries = this.findVerifiedSeries(id);
        Series series = Series.of(seriesUpdateDto.getTitle());

        Series updateSeries = updateUtils.copyNonNullProperties(series,DescSeries);

        return SeriesResponseDto.of(updateSeries.getId(),
                updateSeries.getTitle(),
                updateSeries.getDaylogCount(),
                updateSeries.getCreatedAt(),
                updateSeries.getModifiedAt(),
                updateSeries.getVoteCount(),
                updateSeries.getVoteResult(),
                updateSeries.getVoteAgree(),
                updateSeries.getVoteDisagree(),
                updateSeries.getRevoteResult(),
                updateSeries.getRevoteAgree(),
                updateSeries.getRevoteDisagree(),
                updateSeries.getVoteStatus(),
                updateSeries.getIsPublic(),
                updateSeries.getIsEditable(),
                updateSeries.getIsActive(),
                updateSeries.getMember());

    }

    public void deleteSeries(Long id){

        Series series = this.findVerifiedSeries(id);
        seriesRepository.delete(series);
    }





    public Series findVerifiedSeries(Long seriesId){
        Optional<Series> optionalQuestion = seriesRepository.findById(seriesId);

        Series findSeries =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        return findSeries;

    }

}
