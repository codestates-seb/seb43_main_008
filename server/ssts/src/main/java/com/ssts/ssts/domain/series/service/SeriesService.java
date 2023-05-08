package com.ssts.ssts.domain.series.service;


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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    private final MemberRepository memberRepository;

    private final UpdateUtils<Series> updateUtils;



    public SeriesResponseDto getSeries(Long id){

        Series series = this.findVerifiedSeries(id);

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


    public SeriesResponseDto updateSeries(Long id, SeriesUpdateDto seriesUpdateDto){

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





    public Series findVerifiedSeries(Long questionId){
        Optional<Series> optionalQuestion = seriesRepository.findById(questionId);

        Series findSeries =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        return findSeries;

    }

}
