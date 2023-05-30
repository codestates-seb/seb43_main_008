package com.ssts.ssts.domain.series.service;


import com.ssts.ssts.domain.badges.response.BadgeResponse;
import com.ssts.ssts.domain.bookmark.repository.BookmarkRepository;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.repository.MemberRepository;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.domain.series.constant.SeriesConstants;
import com.ssts.ssts.domain.series.dto.SeriesDetailResponseDto;
import com.ssts.ssts.global.utils.MultipleResponseDto.PageResponseDto;
import com.ssts.ssts.domain.member.repository.MemberVoteRepository;
import com.ssts.ssts.domain.series.dto.SeriesPostDto;
import com.ssts.ssts.domain.series.dto.SeriesResponseDto;
import com.ssts.ssts.domain.series.dto.SeriesUpdateDto;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.S3Uploader;
import com.ssts.ssts.global.utils.UpdateUtils;

import com.ssts.ssts.global.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final MemberService memberService;
    private final UpdateUtils<Series> updateUtils;
    private final S3Uploader s3Uploader;
    //vote
    private final MemberVoteRepository voteMemberRepo;
    private final MemberRepository memberRepo;
    private final BookmarkRepository bookmarkRepo;
    private final VoteService voteService;

    public PageResponseDto getSeriesList(String nickname, int page, int size){
        // 파라미터 체크
        if(nickname.isEmpty()) throw new BusinessLogicException(ExceptionCode.INPUT_NULL);

        Page<Series> seriesInfo;
        Member authMember = memberService.findMemberByToken();
        long memberId = memberService.findMemberByNickName(nickname).getId();

        if(authMember.getId() == memberId) {
            seriesInfo = seriesRepository.findByMember_id(memberId, PageRequest.of(page, size,
                    Sort.by("id").descending()));

        }else if(authMember.getId() != memberId){
            seriesInfo = seriesRepository.findByMember_idAndIsPublicTrue(memberId, PageRequest.of(page, size,
                    Sort.by("id").descending()));
        }else{
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }


        List<Series> seriesList = seriesInfo.getContent();

        //voteLogic
        LocalDateTime currentTime = LocalDateTime.now();

        //마감기간이 지나고 재투표를 진행한 시리즈를 전부 뽑아서 값을 바꿔준거임
        for (int i = 0; i < seriesList.size(); i++) {
            //우선 seriesList전체가 돌고 계산된 값이 들어간 뒤에, if 문으로 해당 값을 바꾸어주어야 함
            //값을 바꿔주는 게 우선임, 그 뒤에 로직을 돌려서 해당 상태값을 바꿔주어야 선행되는 검사를 진행할 수 있음

            if (seriesList.get(i).getVoteCount() == 2 && currentTime.isAfter(seriesList.get(i).getVoteEndAt())) { //재투표 때의 결과값을 먼저 바꿔줌
                seriesList.get(i).setRevoteResult(
                        voteService.voteResultCal(seriesList.get(i).getRevoteAgree(), seriesList.get(i).getRevoteDisagree())
                );
            }

            else if (seriesList.get(i).getVoteCount() == 1 && currentTime.isAfter(seriesList.get(i).getVoteEndAt())) {
                seriesList.get(i).setVoteResult(
                        voteService.voteResultCal(seriesList.get(i).getVoteAgree(), seriesList.get(i).getVoteDisagree())
                );
            }
            seriesRepository.save(seriesList.get(i));
        }



        //상태값 변경
        for (int i = 0; i < seriesList.size(); i++) {
            if(seriesList.get(i).getVoteCount()==2 && currentTime.isAfter(seriesList.get(i).getVoteEndAt())){ //마감기간이 지남 + 투표 2회 이용 (재투표)
                seriesList.get(i).setIsEditable(false);
                seriesList.get(i).setIsActive(false);
                seriesList.get(i).setVoteStatus(Series.VoteStatus.SERIES_QUIT);
            }

            //최초투표: 마감기간이 지나고 결과가 true
            else if(seriesList.get(i).getVoteCount()==1 && currentTime.isAfter(seriesList.get(i).getVoteEndAt()) && seriesList.get(i).getVoteResult()==true){
                seriesList.get(i).setVoteResult(
                        voteService.voteResultCal(seriesList.get(i).getVoteAgree(), seriesList.get(i).getVoteDisagree())
                );


            }

            //최초투표: 마감기간이 지나고 결과가 false
            else if(seriesList.get(i).getVoteCount()==1 && currentTime.isAfter(seriesList.get(i).getVoteEndAt()) && seriesList.get(i).getVoteResult()==false){
                seriesList.get(i).setIsEditable(false);
                seriesList.get(i).setIsActive(false);
                seriesList.get(i).setVoteStatus(Series.VoteStatus.SERIES_SLEEP);
            }

            seriesRepository.save(seriesList.get(i));
        }

        List<SeriesResponseDto> list = this.seriesToSeriesListResponseDtos(seriesList);

        return new PageResponseDto(list, seriesInfo);
    }

    @Transactional
    public PageResponseDto getMySeriesList(int page, int size) {



        Page<Series> seriesInfo;
        Member authMember = memberService.findMemberByToken();

        seriesInfo = seriesRepository.findByMember_id(authMember.getId(), PageRequest.of(page, size,
                Sort.by("id").descending()));


        List<Series> seriesList = seriesInfo.getContent();

        //voteLogic
        LocalDateTime currentTime = LocalDateTime.now();

        //마감기간이 지나고 재투표를 진행한 시리즈를 전부 뽑아서 값을 바꿔준거임
        for (int i = 0; i < seriesList.size(); i++) {
            //우선 seriesList전체가 돌고 계산된 값이 들어간 뒤에, if 문으로 해당 값을 바꾸어주어야 함
            //값을 바꿔주는 게 우선임, 그 뒤에 로직을 돌려서 해당 상태값을 바꿔주어야 선행되는 검사를 진행할 수 있음

            if (seriesList.get(i).getVoteCount() == 2 && currentTime.isAfter(seriesList.get(i).getVoteEndAt())) { //재투표 때의 결과값을 먼저 바꿔줌
                seriesList.get(i).setRevoteResult(
                        voteService.voteResultCal(seriesList.get(i).getRevoteAgree(), seriesList.get(i).getRevoteDisagree())
                );
            }

            else if (seriesList.get(i).getVoteCount() == 1 && currentTime.isAfter(seriesList.get(i).getVoteEndAt())) {
                seriesList.get(i).setVoteResult(
                        voteService.voteResultCal(seriesList.get(i).getVoteAgree(), seriesList.get(i).getVoteDisagree())
                );
            }
            seriesRepository.save(seriesList.get(i));
        }



        //상태값 변경
        for (int i = 0; i < seriesList.size(); i++) {
            if(seriesList.get(i).getVoteCount()==2 && currentTime.isAfter(seriesList.get(i).getVoteEndAt())){ //마감기간이 지남 + 투표 2회 이용 (재투표)
                seriesList.get(i).setIsEditable(false);
                seriesList.get(i).setIsActive(false);
                seriesList.get(i).setVoteStatus(Series.VoteStatus.SERIES_QUIT);
            }

            //최초투표: 마감기간이 지나고 결과가 true => 이 경우에 별거 건들지 않음
            else if(seriesList.get(i).getVoteCount()==1 && currentTime.isAfter(seriesList.get(i).getVoteEndAt()) && seriesList.get(i).getVoteResult()==true){
                seriesList.get(i).setVoteResult(
                        voteService.voteResultCal(seriesList.get(i).getVoteAgree(), seriesList.get(i).getVoteDisagree())
                );
            }

            //최초투표: 마감기간이 지나고 결과가 false
            //[마감기한이 지났지만, 재투표의 기회가 있는 경우의 상태 변경] => 활성 상태는 변경하지 않고, 수정이 불가능
            else if(seriesList.get(i).getVoteCount()==1 && currentTime.isAfter(seriesList.get(i).getVoteEndAt()) && seriesList.get(i).getVoteResult()==false){
                seriesList.get(i).setIsEditable(false);
                seriesList.get(i).setIsActive(false);
                seriesList.get(i).setVoteStatus(Series.VoteStatus.SERIES_SLEEP);
            }

            seriesRepository.save(seriesList.get(i));
        }

        List<SeriesResponseDto> list = this.seriesToSeriesListResponseDtos(seriesList);

        return new PageResponseDto(list, seriesInfo);
    }

    public PageResponseDto getMainSeriesListByNewest(int page, int size){

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기



        Page<Series> seriesInfo = seriesRepository.findAllByIsPublicAndVoteCreatedAtIsNotNull(true, PageRequest.of(page, size,
                Sort.by("voteCreatedAt").descending()));

        List<Series> seriesList = seriesInfo.getContent();
        List<SeriesResponseDto> list = this.seriesToSeriesListResponseDtos(seriesList);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);

        return new PageResponseDto(list, seriesInfo);
    }

    public PageResponseDto getMainSeriesListByVotes(int page, int size){

        Page<Series> seriesInfo = seriesRepository.findAllByIsPublicAndVoteStatusAndTotalVoteNot(true, Series.VoteStatus.SERIES_SLEEP,0, PageRequest.of(page, size,
                Sort.by("totalVote").descending()));

        List<Series> seriesList = seriesInfo.getContent();
        List<SeriesResponseDto> list = this.seriesToSeriesListResponseDtos(seriesList);

        return new PageResponseDto(list, seriesInfo);
    }

    public SeriesDetailResponseDto getSeries(Long id){
        memberService.findMemberByToken();
        Series series = this.findVerifiedSeries(id);

        //사용자 Id받기
        long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        //vote: 사용자의 vote 여부를 응답으로 보내기 위함
        //Boolean isVotedMember = voteMemberRepo.existsByMember_IdAndSeries_Id(memberId, id);

        //bookmark: 사용자의 해당 시리즈 북마크 여부
        Boolean isBookmarkedMember = bookmarkRepo.existsByMember_IdAndSeries_Id(memberId, id);

        return this.seriesToSeriesResponseDto(series, isBookmarkedMember);
    }



    @Transactional
    public SeriesResponseDto saveSeries(String isPublic, SeriesPostDto seriesPostDto){
        // 파라미터 체크 완료
        if(seriesPostDto.getTitle().isEmpty()){
            throw new BusinessLogicException(ExceptionCode.INPUT_IS_NOT_ALLOWED);
        }

        Member authMember = memberService.findMemberByToken();
        Series series = Series.of(seriesPostDto.getTitle());
        Member member = memberService.findMemberById(authMember.getId());

        if(authMember.getId()!=member.getId()){
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }

        if("true".equals(isPublic)){
            series.setIsPublic(true);
        }else if("false".equals(isPublic)){
            series.setIsPublic(false);
        }else {
            throw new BusinessLogicException(ExceptionCode.INPUT_IS_NOT_ALLOWED);
        }

        series.setImage(s3Uploader.getS3(SeriesConstants.BUCKET_NAME.getSeriesConstant(), SeriesConstants.FILE_DiRECTORY.getSeriesConstant())); // 상수 선언
        series.addMember(member);
        seriesRepository.save(series);

        return this.seriesToSeriesResponseDto(series);
    }

    @Transactional
    public SeriesResponseDto updateSeries(Long seriesId, SeriesUpdateDto seriesUpdateDto){
        // 파라미터 체크 완료


        Member member = memberService.findMemberByToken();
        Series descSeries = this.findVerifiedSeries(seriesId);
        Series series = Series.of(seriesUpdateDto.getTitle(), seriesUpdateDto.getIsPublic());

        if(descSeries.getIsEditable().equals(false)){
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }
        if(descSeries.getMember().getId() != member.getId()){ // 스트링값 equal로비교
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }

        Series updateSeries = updateUtils.copyNonNullProperties(series,descSeries);
        seriesRepository.save(updateSeries);

        return this.seriesToSeriesResponseDto(updateSeries);
    }

    @Transactional
    public void deleteSeries(Long seriesId){
        Member member = memberService.findMemberByToken();
        Series series = this.findVerifiedSeries(seriesId);
        if(series.getMember().getId()!=member.getId()){
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }
        seriesRepository.delete(series);
    }




    public Series findVerifiedSeries(Long seriesId){
        Optional<Series> optionalSeries = seriesRepository.findById(seriesId);

        Series findSeries =
                optionalSeries.orElseThrow(() ->
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
                series.getIsActive(),
                series.getTotalVote());
    }


    //getSerise
    @NotNull
    private SeriesResponseDto seriesToSeriesResponseDto(Series series, Boolean isVotedMember, Boolean isBookmarkedMember) {
        //선언부(메서드 시그니처)가 메소드 오버로드에 중심
        //메소드의 이름이 같아도, 파라미터와 반환값이 다르면 얘가 알아서 분리해서 적용햅줌
        //이걸로 오버로드를 사용해서 여러개의 파라미터를 받는 같은 메소드를 구현 가능


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
                series.getIsActive(),
                isVotedMember,
                isBookmarkedMember
        );
    }

    @NotNull
    private SeriesDetailResponseDto seriesToSeriesResponseDto(Series series, Boolean isBookmarkedMember) {
        //선언부(메서드 시그니처)가 메소드 오버로드에 중심
        //메소드의 이름이 같아도, 파라미터와 반환값이 다르면 얘가 알아서 분리해서 적용햅줌
        //이걸로 오버로드를 사용해서 여러개의 파라미터를 받는 같은 메소드를 구현 가능

        return SeriesDetailResponseDto.of(series.getId(), series.getTitle(), series.getImage(), isBookmarkedMember
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
