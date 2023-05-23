package com.ssts.ssts.domain.series.service;


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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
        List<SeriesResponseDto> list = this.seriesToSeriesListResponseDtos(seriesList);

        return new PageResponseDto(list, seriesInfo);
    }

    public PageResponseDto getMySeriesList(int page, int size){
        // 파라미터 체크

        Page<Series> seriesInfo;
        Member authMember = memberService.findMemberByToken();

        seriesInfo = seriesRepository.findByMember_id(authMember.getId(), PageRequest.of(page, size,
                    Sort.by("id").descending()));


        List<Series> seriesList = seriesInfo.getContent();
        List<SeriesResponseDto> list = this.seriesToSeriesListResponseDtos(seriesList);


        return new PageResponseDto(list, seriesInfo);
    }

    public PageResponseDto getMainSeriesListByNewest(int page, int size){

        Page<Series> seriesInfo = seriesRepository.findAllByIsPublicAndVoteCreatedAtIsNotNull(true, PageRequest.of(page, size,
                Sort.by("voteCreatedAt")));

        List<Series> seriesList = seriesInfo.getContent();
        List<SeriesResponseDto> list = this.seriesToSeriesListResponseDtos(seriesList);

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
        Boolean isVotedMember = voteMemberRepo.existsByMember_IdAndSeries_Id(memberId, id);

        //bookmark: 사용자의 해당 시리즈 북마크 여부
        Boolean isBookmarkedMember = bookmarkRepo.existsByMember_IdAndSeries_Id(memberId, id);



        //vote: 사용자가 조회할 때마다 마감 기간 계산하고, 그에 따른 투표 상태값 변경 (마감시 자동 상태값 변경)
        LocalDateTime currentTime = LocalDateTime.now();

        //[마감 기한이 지난 경우 + 투표 2회를 전부 진행한 경우 상태 변경]
        if (series.getVoteCount()==2 && currentTime.isAfter(series.getVoteEndAt())) {
            series.setIsEditable(false); //타이틀 수정 불가능
            series.setIsActive(false); //활성 상태 끄기 (프론트 세피아처리)
            series.setVoteStatus(Series.VoteStatus.SERIES_QUIT); //투표에 할당

            //투표 2회의 로직이 전부 끝난 이후에, 결과를 저장함
            //set revote Result
            Boolean revoteResult = voteService.voteResultCal(series.getRevoteAgree(), series.getRevoteDisagree());
            series.setRevoteResult(revoteResult);

            seriesRepository.save(series);
        }

        else if(series.getVoteCount()==1 && !currentTime.isAfter(series.getVoteEndAt())){ //마감기간 안지났을 때
            //return this.seriesToSeriesResponseDto(series, isVotedMember, isBookmarkedMember);
            return this.seriesToSeriesResponseDto(series, isBookmarkedMember);
        }



        //마감기간 지났을 때
        //투표가 종료되고 나서 계산기를 돌리고 true면 그냥 내보내고 false면 상태값 변경

        else if(series.getVoteCount()==1 && currentTime.isAfter(series.getVoteEndAt())){ //마감기간 지났을 때

            //마감기간 지나고 난 뒤의 계산기를 돌려야 함
            Boolean voteResult = voteService.voteResultCal(series.getVoteAgree(), series.getVoteDisagree());

            //결과가 찬성일 경우: 그대로 변경없이 로직을 내보냄
            if(voteResult){
                series.setVoteResult(voteResult);
                seriesRepository.save(series);
                return this.seriesToSeriesResponseDto(series, isBookmarkedMember);
            }

            //결과가 반대일 경우: 재투표의 기회가 있기 때문에 특정 값을 바꿔서 내보냄
            else if(!voteResult){

            series.setIsEditable(false); //수정 불가능
            series.setIsActive(false);
            series.setVoteStatus(Series.VoteStatus.SERIES_SLEEP);

            series.setVoteResult(voteResult);

            seriesRepository.save(series); //사용자가 재투표를 받을지 말지 선택하기 전까지 유지되는 상태값

            }
        }
        return this.seriesToSeriesResponseDto(series, isBookmarkedMember);
    }



    @Transactional
    public SeriesResponseDto saveSeries(String isPublic, SeriesPostDto seriesPostDto){
        // 파라미터 체크


        Member authMember = memberService.findMemberByToken();
        Series series = Series.of(seriesPostDto.getTitle());
        Member member = memberService.findMemberById(authMember.getId());

        if(authMember.getId()!=member.getId()){
            throw new BusinessLogicException(ExceptionCode.NOT_ALLOWED_PERMISSION);
        }

        if("true".equals(isPublic)){
            series.setIsPublic(true);
            // 예외처리
        }

        series.setImage(s3Uploader.getS3("ssts-img", SeriesConstants.FILE_NAME.getSeriesConstant())); // 상수 선언
        series.addMember(member);
        seriesRepository.save(series);

        return this.seriesToSeriesResponseDto(series);
    }

    @Transactional
    public SeriesResponseDto updateSeries(Long seriesId, SeriesUpdateDto seriesUpdateDto){
        // 파라미터 체크
        Member member = memberService.findMemberByToken();
        Series descSeries = this.findVerifiedSeries(seriesId);
        Series series = Series.of(seriesUpdateDto.getTitle(), seriesUpdateDto.getIsPublic());

        if(descSeries.getIsEditable().equals(false)){ // 반대로 false.equ~
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
