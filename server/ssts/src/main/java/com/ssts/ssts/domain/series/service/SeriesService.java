package com.ssts.ssts.domain.series.service;


import com.ssts.ssts.domain.daylog.entity.Daylog;
import com.ssts.ssts.domain.daylog.repository.DaylogRepository;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.domain.member.repository.MemberVoteRepository;
import com.ssts.ssts.domain.series.dto.SeriesPageResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesPostDto;
import com.ssts.ssts.domain.series.dto.SeriesResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesUpdateDto;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import com.ssts.ssts.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    private final MemberRepository memberRepository;

    private final UpdateUtils<Series> updateUtils;

    //vote
    private final MemberVoteRepository voteMemberRepo;

    public SeriesPageResponseDto getSeriesList(Long memberid, int page, int size){

        Page<Series> seriesInfo = seriesRepository.findByMember_id(memberid, PageRequest.of(page, size,
                Sort.by("id").descending()));

        List<Series> seriesList = seriesInfo.getContent();

        List<SeriesResponseDto> list = this.seriesToSeriesListResponseDtos(seriesList);

        return new SeriesPageResponseDto(list, seriesInfo);
    }

    public SeriesResponseDto getSeries(Long id){

        Series series = this.findVerifiedSeries(id);

        //vote: 사용자의 vote 여부를 받기 위함
        Boolean isVotedMember = voteMemberRepo.existsByMember_IdAndSeries_Id(1L, id);

        //vote를 만들지 않으면 시리즈 조회가 안됨

        //vote: 사용자가 조회할 때마다 마감 기간 계산하고, 그에 따른 투표 상태값 변경 (마감시 자동 상태값 변경)
        LocalDateTime currentTime = LocalDateTime.now(); //한국 기준으로 현재 시간 얻기 (LocalDateTIme 객체 반환)

        //[마감 기한이 지난 경우 + 투표 2회를 전부 진행한 경우 상태 변경]
        if (series.getVoteCount()==2 && currentTime.isAfter(series.getVoteEndAt())) {
            series.setEditable(false); //타이틀 수정 불가능
            series.setActive(false); //활성 상태 끄기능 (프론트 세피아처리)
            series.setSeriesStatus(Series.VoteStatus.SERIES_QUIT); //투표에 할당
            seriesRepository.save(series);
        }

        //사용자가 재투표 연다는 선택을 하기 전, 자동으로 바뀌는 값임
        //[마감기한이 지났지만, 재투표의 기회가 있는 경우의 상태 변경] => 활성 상태는 변경하지 않고, 수정이 자동으로 가능하도록 합니다. //리팩토링 부분: vote는 voteCount==1이기 때문에 voteCount를 꼭 써야 하는가? ㅇㅇㅇㅇ api 진입점때문에
        else if (series.getVoteCount()==1 && currentTime.isAfter(series.getVoteEndAt()) && series.getVoteResult()==false){
            series.setEditable(false); //수정 불가능
            series.setActive(false);
            series.setSeriesStatus(Series.VoteStatus.SERIES_QUIT);
            //ㄴ> 재시도의 기회가 있고, 재시도를 하지 않는 사용자의 경우는 투표 종료 버튼을 누른다
            //ㄴ> 투표를 재시도 한다고 선택하지 않는 이상, serise의 상태는 SERIES_QUIT가 됨
            //ㄴ> 사용자가 확인하기 전 -> 사용자가
            seriesRepository.save(series);
        }
        //ㄴ> 활성 상태를 그대로 켜는 이유는, 재투표를 할지 말지에 대한 선택을 따로 진행하기 때문

        return this.seriesToSeriesResponseDto(series, isVotedMember);
    }




    public SeriesResponseDto saveSeries(Long memberId, SeriesPostDto seriesPostDto){

        Series series = Series.of(seriesPostDto.getTitle());
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        series.addMember(findMember);
        seriesRepository.save(series);

        return this.seriesToSeriesResponseDto(series);
    }


    public SeriesResponseDto updateSeries(Long memeberId, Long id, SeriesUpdateDto seriesUpdateDto){

        Series DescSeries = this.findVerifiedSeries(id);
        Series series = Series.of(seriesUpdateDto.getTitle());

        Series updateSeries = updateUtils.copyNonNullProperties(series,DescSeries);

        return this.seriesToSeriesResponseDto(series);

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

    @NotNull
    private SeriesResponseDto seriesToSeriesResponseDto(Series series) {


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
                series.getIsActive());
    }

    //getSerise
    @NotNull
    private SeriesResponseDto seriesToSeriesResponseDto(Series series, Boolean isVotedMember) {
        //선언부(메서드 시그니처)가 메소드 오버로드에 중심
        //메소드의 이름이 같아도, 파라미터와 반환값이 다르면 얘가 알아서 분리해서 적용햅줌
        //이걸로 오버로드를 사용해서 여러개의 파라미터를 받는 같은 메소드를 구현 가능


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
                isVotedMember
        );
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
