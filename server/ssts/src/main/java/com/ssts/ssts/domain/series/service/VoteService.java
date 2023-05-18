package com.ssts.ssts.domain.series.service;

import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.entity.MemberVote;
import com.ssts.ssts.domain.member.repository.MemberRepository;

import com.ssts.ssts.domain.member.repository.MemberVoteRepository;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.domain.series.response.vote.VoteResponse;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//시간 계산 import
import java.time.*;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final SeriesRepository seriesRepo;
    private final MemberVoteRepository voteMemberRepo;
    private final MemberRepository memberRepo;

    //투표 생성
    @Transactional
    public Object createVote(Long seriesId) {

        Series targetSeries = seriesRepo.findById(seriesId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS)); //투표를 생성할 Series Entity 찾기

        //TODO *-토큰Id적용--* TODO토큰시에 주석풀기
        long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        if(memberId != targetSeries.getMember().getId()){ throw new BusinessLogicException(ExceptionCode.NOT_SERISE_WRITER); }


        //(voteCount>2) 더이상 투표를 개설할 수 없습니다
        if (targetSeries.getVoteCount()==2){ throw new BusinessLogicException(ExceptionCode.CAN_NOT_MAKE_VOTE); }

        //마감기간이 지나지 않았으면 투표를 생성할 수 없음 예외 (재투표일때만 해당 예외)
        if(targetSeries.getVoteCount()==1){ //count==1을 먼저 검사하지 않으면 마감 기한이 없을 때 NullPointer 뜸 (&& 불가)

            //(5트) 마감기간 지나지 않은 것에 대한 메소드 2번 사용, 외부 메소드 처리
            if(isVotedNotEntAt(targetSeries)){
                throw new BusinessLogicException(ExceptionCode.DEADLINE_FALL_SHORT);
            }

        }

        //투표에 따른 상태값 변경 //of를 쓴 게 아닌데 일단은 냅 두기 / 리팩토링 대상
        targetSeries.setPublic(true); //시리즈 공개
        targetSeries.setIsEditable(false); //타이틀 수정 불가
        targetSeries.setIsActive(true); //활성 상태
        targetSeries.setVoteStatus(Series.VoteStatus.SERIES_SLEEP); //투표중 할당
        targetSeries.setVoteCount(targetSeries.getVoteCount() + 1); //최초투표이든, 아니든 +1 //투표함을 만들 때, voteCount가 증가

        //투표 생성시간 할당
        targetSeries.setVoteCreatedAt(LocalDateTime.now());
        //투표 마감기간 (2일) 할당
        //targetSeries.setVoteEndAt(targetSeries.getVoteCreatedAt().plusDays(2));
        targetSeries.setVoteEndAt(targetSeries.getVoteCreatedAt().plusSeconds(15));
        //ㄴ> 테스트 마감기간: 15초

        //재투표시에 memberVote 초기화 (중복 제거)
        if (targetSeries.getVoteCount() == 2) { voteMemberRepo.deleteAllBySeries_Id(seriesId); }


        seriesRepo.save(targetSeries);
        return voteCountResponse(seriesId, targetSeries);
    }


    //투표하기
    @Transactional
    public VoteResponse attendVote(Long seriesId, int isAgree){ //TODO 토큰 테스트시에 주석 풀기
    //public Object attendVote(Long seriesId, int isAgree, Long memberId) { //@@토큰 미사용 로직@@

        if(isAgree < 0 || isAgree > 1){throw new BusinessLogicException(ExceptionCode.CAN_NOT_VOTE_VALUE);}

        //TODO *-토큰Id적용--* TODO토큰시에 주석풀기
        long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));


        //시리즈가 존재하지 않습니다.
        Series targetSeries = seriesRepo.findById(seriesId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));
            int voteCount = targetSeries.getVoteCount(); //응답값 만들려고 존재
            if(voteCount == 0){throw new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND);}


        //동일 시리즈에 중복 투표 예외
        Boolean isVotedMember = voteMemberRepo.existsByMember_IdAndSeries_Id(memberId, seriesId); //true => false
            if (isVotedMember) { throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_VOTE); }

        //사용자가 존재하지 않습니다
        Member voteMember = memberRepo.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));;

        //투표하기 로직
        //최초투표
        if (targetSeries.getVoteCount() == 1) {
            //경우1: 최초 투표일 경우
            if (isAgree == 1) {//본래 있는 db의 컬럼을 바꿔줌
                targetSeries.setVoteAgree(targetSeries.getVoteAgree() + 1);
                targetSeries.setTotalVote(targetSeries.getTotalVote()+1);
            } else if (isAgree == 0) {
                targetSeries.setVoteDisagree(targetSeries.getVoteDisagree() + 1);
                targetSeries.setTotalVote(targetSeries.getTotalVote()+1);
            } else {
                return voteCountResponse(targetSeries.getId(),targetSeries);
            }

            //set vote Result
            targetSeries.setVoteResult(voteResultCal(targetSeries.getVoteAgree(), targetSeries.getVoteDisagree()));
            //save mapping table
            voteMemberRepo.save(MemberVote.of(voteMember, targetSeries, isAgree));

            return voteCountResponse(targetSeries.getId(), targetSeries);
        }


        //재투표
        else if (voteCount == 2) {
            targetSeries.setTotalVote(0);
            if (isAgree == 1) { //찬성
                targetSeries.setRevoteAgree(targetSeries.getRevoteAgree() + 1);
                targetSeries.setTotalVote(targetSeries.getTotalVote()+1);
            } else if (isAgree == 0) { //반대
                targetSeries.setRevoteDisagree(targetSeries.getRevoteDisagree() + 1);
                targetSeries.setTotalVote(targetSeries.getTotalVote()+1);
            }

            //set revote Result
            targetSeries.setRevoteResult(voteResultCal(targetSeries.getRevoteAgree(), targetSeries.getRevoteDisagree()));
            //save mapping table
            voteMemberRepo.save(MemberVote.of(voteMember, targetSeries, isAgree));

            return voteCountResponse(targetSeries.getId(), targetSeries);
        }


        //Exception: 존재하지 않는 투표
        return voteCountResponse(targetSeries.getId(), targetSeries);
    }

    //투표 종료하기
    //정상적 종료: 마감기간이 지난 뒤에 자동종료: getSerise에서 진행
    //[Boolean isQuit: 투표를 더 할지 말지 결정하는 값 (더 안한다:1) / (더 한다:0)]
    //비정상 종료 로직이 여기 있는 로직 (voteCount ==1 && voteResult ==false) / 최초투표 진행한 사용자가, 더 투표를 받을 것인지 그대로 졸업시킬 것인지에 대한 로직
    //[마감 1개 하고 재투표 없이 종료하는 경우] => 새 페이지 처리
    @Transactional //[모든 예외를 거치고 남은 걸려져서 들어오는 값이 종료하기의 조건이 되도록]
    //public Object quitVote(Long seriesId,  Long memberId, Boolean isQuit){
    public Object quitVote(Long seriesId, Boolean isQuit){ //TODO 토큰 적용시에 풀기

        //TODO *-토큰Id적용--* TODO토큰시에 주석풀기
        long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Series targetSeries = seriesRepo.findById(seriesId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        //예외: 투표를 개설한 본인이 아닙니다 //TODO 토큰
        if(targetSeries.getMember().getId() != memberId){
            //투표를 개설한 본인이 아닙니다
            throw new BusinessLogicException(ExceptionCode.NOT_HAVE_VOTE_AUTHORITY);
        }

        //예외: 투표를 개설하지 않았습니다
        if (targetSeries.getVoteCount()==0) {
            throw new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND);
        }

        //예외: 투표의 총 횟수를 다 씀 (voteCount==2 여기서 거른다)
        if(targetSeries.getVoteCount()!=1){
            //투표 종료에 대한 권한이 없습니다
            throw new BusinessLogicException(ExceptionCode.NOT_HAVE_VOTE_AUTHORITY);
        }


        //[리팩토링 진입점: voteCount는 2부터 걸러지기 때문에, 1밖에 안들어옴. 조건에 1이 달릴 이유가 있나?]
        if(targetSeries.getVoteResult()==null){
            //투표 결과가 존재하지 않습니다
            throw new BusinessLogicException(ExceptionCode.VOTE_RESULT_IS_NOT_EXSIST);
        }


        //예외: 최초투표를 진행함, 최초투표에서 찬성 결과가 나옴 (voteCount==1 && voteResult==true)
        if(targetSeries.getVoteResult()){
            //이 투표는 이미 졸업했어요!
            throw new BusinessLogicException(ExceptionCode.THIS_VOTE_RESULT_IS_TRUE);
        }

        //예외: 최초투표를 진행했는데, 마감기한이 지나지 않은 상태 (voteCount==1 && 마감기간이 지나지 않은 경우)
        if(isVotedNotEntAt(targetSeries)){
            throw new BusinessLogicException(ExceptionCode.DEADLINE_FALL_SHORT);
        }

        //걸려지는 경우 (1) 투표를 더 한다고 선택 ( && voteCount==1)
        if(!isQuit){ //(isQuit==false)
            targetSeries.setIsEditable(true);
            targetSeries.setIsActive(true);
            targetSeries.setVoteStatus(Series.VoteStatus.SERIES_ACTIVE);
        }

        //걸러지는 경우 (2) / 최종: 투표를 더 안할게요 voteCount==1&&voteResult==false => 로직이 도는 대상 (isQuit==1)
        else {
        targetSeries.setIsEditable(false); //타이틀 수정 가능
        targetSeries.setIsActive(false); //활성 상태
        targetSeries.setVoteStatus(Series.VoteStatus.SERIES_QUIT); //투표에 할당
        }
        return voteCountResponse(targetSeries.getId(), targetSeries);
    }


    //로직: 마감기한 지났는지의 여부
    private Boolean isVotedNotEntAt(Series targetSeries){ //마감기한 안지남: true
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.isBefore(targetSeries.getVoteEndAt());
    }


    @NotNull //로직: 시리즈 Id를 통해 Series 객체 꺼내기
    private Series findSeriesById(Long seriesId) {
        Optional<Series> findSeries = seriesRepo.findById(seriesId);
        Series targetSeries = findSeries.get();
        return targetSeries;
    }


    //내부 로직: 투표 횟수별 검증 메소드 (투표 전체 정보)
    public VoteResponse voteCountResponse(Long seriesId, Series targetSeries) {
        //이제 voteCount++ 된 상태이기 때문에 여기서부터 최초투표인지 재투표인지 알 수 있음

        if (targetSeries.getVoteCount() == 1) {
            return VoteResponse.FirstVoteResponse.of(
                    seriesId,
                    targetSeries.getVoteCount(),
                    targetSeries.getVoteResult(),
                    targetSeries.getVoteAgree(),
                    targetSeries.getVoteDisagree(),
                    targetSeries.getVoteStatus(),
                    targetSeries.getVoteCreatedAt(),
                    targetSeries.getVoteEndAt()
            );

        } else if (targetSeries.getVoteCount() == 2) {
            return VoteResponse.RevoteResponse.of(
                    seriesId,
                    targetSeries.getVoteCount(),
                    targetSeries.getRevoteResult(),
                    targetSeries.getRevoteAgree(),
                    targetSeries.getRevoteDisagree(),
                    targetSeries.getVoteStatus(),
                    targetSeries.getVoteCreatedAt(),
                    targetSeries.getVoteEndAt()
            );
        }else { throw new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND); }
    }


    //voteResult 계산기
    public Boolean voteResultCal(int agree, int disAgree) {
        if (agree > disAgree) {
            return true;
        } else if (agree == disAgree) {
            return true; //하늘님: 동점이면 난 버릴 거 같아요 ! }
            //
        }
        return false;
    }

}
