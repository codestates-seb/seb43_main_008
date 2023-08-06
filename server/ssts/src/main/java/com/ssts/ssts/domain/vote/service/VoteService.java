package com.ssts.ssts.domain.vote.service;

import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.entity.MemberVote;
import com.ssts.ssts.domain.member.repository.MemberRepository;

import com.ssts.ssts.domain.member.repository.MemberVoteRepository;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.domain.vote.entity.Vote;
import com.ssts.ssts.domain.vote.repository.VoteRepository;
import com.ssts.ssts.domain.vote.response.VoteResponse;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//시간 계산 import
import java.time.*;

//VoteDefaultResponse == FirstVoteResponse

@Service
@RequiredArgsConstructor
public class VoteService {
    private final SeriesRepository seriesRepo;
    private final MemberVoteRepository voteMemberRepo;
    private final MemberRepository memberRepo;
    private final MemberService memberService;
    private final VoteRepository voteRepo;

//투표 생성: refactor
    @Transactional
    public Object createVote(Long seriesId) {

        //투표함 예외
        //시리즈를 작성한 본인이 아니라면 예외 (완)
        //더 이상 투표 불가 (voteCount==2) (완)
        //마감기간이 지나지 않았으면 투표를 생성할 수 없음 예외 (완)
        //최초투표의 결과가 조회되지 않았어요 > @@투표하기 만든 이후에 다시 검증
        //데이로그 안 만들었으면 터지는 에러

        Series targetSeries = seriesRepo.findById(seriesId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS)); //투표를 생성할 Series Entity 찾기

        //series에서 vote 찾아오기
        //Vote targetVote = voteRepo.findBySeries_Id(seriesId).orElseThrow(()->new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND));
        //TODO 투표함을 만드는 건데 예외가 터지면 안되는 거구나 ...
        //아니 오히려 새로운 투표를 만드는건데?

        Member member = memberService.getMemberByToken();
        long memberId = member.getId();

        //TODO 시리즈를 만든 작성자가 아닌 자가 투표 생성시 예외 왜 이거 안되는거지?
        if(memberId != targetSeries.getMember().getId()){ throw new BusinessLogicException(ExceptionCode.NOT_SERISE_WRITER); }



        Long voteCount= voteRepo.countBySeries(targetSeries);
        int seriesVoteCount = voteCount.intValue();

        //TODO 더이상 투표를 개설할 수 없습니다.
        if(seriesVoteCount>=2){
            throw new BusinessLogicException(ExceptionCode.CAN_NOT_MAKE_VOTE);
        }

        //TODO 최초투표가 존재할 경우, 재투표 생성 전 예외들
        if(seriesVoteCount ==1){
            revoteException(targetSeries.getId());
        }

       //TODO 현재 목표는 voteEntity에 데이터를 저장하고, series의 값을 바꿔주는 것임 => voteEntity 객체를 생성해서 저장해주는 일을 해야지 ㅇㅇㅇ!!!
        //투표에 따른 상태값 변경 //of를 쓴 게 아닌데 일단은 냅 두기 / 리팩토링 대상
        targetSeries.setIsPublic(true); //시리즈 공개
        targetSeries.setIsEditable(false); //타이틀 수정 불가
        targetSeries.setIsActive(true); //활성 상태



        Vote targetVote = Vote.of(Vote.VoteStatus.SERIES_SLEEP, LocalDateTime.now());
        targetVote.setSeries(targetSeries); // 시리즈와 연결
        //targetVote.setVoteEndAt(targetVote.getVoteCreatedAt().plusMinutes(5)); //투표 마감기간, //객체가 생성되어야 그 뒤의 것들을 할 수 있으니까
        targetVote.setVoteEndAt(targetVote.getVoteCreatedAt().plusSeconds(7));
        //ㄴ> 여기까지는 최초투표, 재투표 공통 시리즈, 투표 상태


        //if(userVoteCount >=2){} //다른 컬럼이라 +1, +2 이런걸 못함
        if(seriesVoteCount ==0){
            targetVote.setVoteCount(1);
        }
        else{ //(userVoteCount ==1)의 상황, 앞에서 이미 다 걸럿음
            targetVote.setVoteCount(2);
        }

        seriesRepo.save(targetSeries);
        voteRepo.save(targetVote);
        return voteResponse(targetSeries.getId(), targetVote);
    }



    //TODO 투표하기
    @Transactional
    public VoteResponse attendVote(Long voteId, int isAgree){ //TODO 토큰 테스트시에 주석 풀기


        //Vote targetVote = voteRepo.findBySeries_Id(seriesId).orElseThrow(()->new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND));
        Vote targetVote = voteRepo.findById(voteId).orElseThrow(()->new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND));
        Series targetSeries = seriesRepo.findByVotes_Id(voteId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));
        Long seriesId = targetSeries.getId();

        if(isAgree < 0 || isAgree > 1){throw new BusinessLogicException(ExceptionCode.CAN_NOT_VOTE_VALUE);}

        Member member = memberService.getMemberByToken();
        long memberId = member.getId();

        //동일 시리즈에 중복 투표 예외
        Boolean isVotedMember = voteMemberRepo.existsByMember_IdAndVote_Id(memberId, voteId); //true => false
        if (isVotedMember) { throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_VOTE); }

        //이미 종료된 투표입니다
        //if(!isVotedNotEntAt(targetVote)){ throw new BusinessLogicException(ExceptionCode.VOTE_ALREADY_FINISH); }

        //TODO 시리즈가 존재하지 않습니다. @@@@voteId를 통해 series 찾기
        seriesRepo.findByVotes_Id(voteId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        int voteCount = targetVote.getVoteCount(); //응답값 만들려고 존재
        if(voteCount == 0){throw new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND);}

        //사용자가 존재하지 않습니다
        Member voteMember = memberRepo.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));


        //투표하기 로직
        //경우1: 최초 투표일 경우
            if (isAgree == 1) {//본래 있는 db의 컬럼을 바꿔줌 -> 위에서 isAgree 값을 거르고 들어옴
                targetVote.setAgree(targetVote.getAgree() + 1);
                targetVote.setVotePapers(targetVote.getVotePapers()+1);
            } else {//if (isAgree == 0) {
                targetVote.setDisagree(targetVote.getDisagree() + 1);
                targetVote.setVotePapers(targetVote.getVotePapers() + 1);
            }




        //TODO 투표의 결과를 보기 위해서 임시로 사용 -> 프론트 협업시 지워야 함 !!!!!
//                Boolean voteResult = voteResultCal(targetVote.getAgree(), targetVote.getDisagree());
//                targetVote.setVoteResult(voteResult);



        //save mapping table + db save
        voteMemberRepo.save(MemberVote.of(voteMember, targetVote, isAgree));
        voteRepo.save(targetVote);


        return voteResponse(seriesId, targetVote);
    }


    //TODO 투표 종료하기 (false: 재투표 받을게요) / (true: 졸업시킬게요!)
    @Transactional //[모든 예외를 거치고 남은 걸려져서 들어오는 값이 종료하기의 조건이 되도록]
    //public Object quitVote(Long seriesId,  Long memberId, Boolean isQuit){
    public VoteResponse quitVote(Long voteId, Boolean isQuit){ //TODO 토큰 적용시에 풀기

        Member member = memberService.getMemberByToken();
        long memberId = member.getId();

        Vote targetVote = voteRepo.findById(voteId).orElseThrow(()->new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND));
        Series targetSeries = seriesRepo.findByVotes_Id(voteId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));
        Long seriesId = targetSeries.getId();

        //예외: 투표를 개설한 본인이 아닙니다 TODO 이건 vote -> series -> member 로 접근할 수 있도록 수정해야 함
        //vote에서 series를 가져오는데 여기에는 member_id가 있음, 즉 series가 vote를 품고 있기 때문에 targetSeries에서 memberId만 조회해도 해당 예외는 알아서 검증되는 것임
        if(targetSeries.getMember().getId() != memberId){
            //투표를 개설한 본인이 아닙니다
            throw new BusinessLogicException(ExceptionCode.NOT_HAVE_VOTE_AUTHORITY);
        }


        //예외: 투표를 개설하지 않았습니다
        if (targetVote.getVoteCount()==0) {
            throw new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND);
        }
        //예외: 투표의 총 횟수를 다 씀 (voteCount==2 여기서 거른다)
        if(targetVote.getVoteCount()!=1){
            //투표 종료에 대한 권한이 없습니다
            throw new BusinessLogicException(ExceptionCode.NOT_HAVE_VOTE_AUTHORITY);
        }


        //[리팩토링 진입점: voteCount는 2부터 걸러지기 때문에, 1밖에 안들어옴. 조건에 1이 달릴 이유가 있나?]
        if(targetVote.getVoteResult()==null){
            //투표 결과가 존재하지 않습니다
            throw new BusinessLogicException(ExceptionCode.VOTE_RESULT_IS_NOT_EXSIST);
        }


        //예외: 최초투표를 진행함, 최초투표에서 찬성 결과가 나옴 (voteCount==1 && voteResult==true)
        if(targetVote.getVoteResult()){
            //이 투표는 이미 졸업했어요!
            throw new BusinessLogicException(ExceptionCode.THIS_VOTE_RESULT_IS_TRUE);
        }

        //예외: 최초투표를 진행했는데, 마감기한이 지나지 않은 상태 (voteCount==1 && 마감기간이 지나지 않은 경우)
        if(isVotedNotEntAt(targetVote)){
            throw new BusinessLogicException(ExceptionCode.DEADLINE_FALL_SHORT);
        }

        //걸려지는 경우 (1) 투표를 더 한다고 선택 ( && voteCount==1)
        if(!isQuit){ //(isQuit==false)
            targetSeries.setIsEditable(true);
            targetSeries.setIsActive(true);
            targetVote.setStatus(Vote.VoteStatus.SERIES_ACTIVE);
            seriesRepo.save(targetSeries);
            voteRepo.save(targetVote);
        }

        //걸러지는 경우 (2) / 최종: 투표를 더 안할게요 voteCount==1&&voteResult==false => 로직이 도는 대상 (isQuit==1)
        else {
        targetSeries.setIsEditable(false); //타이틀 수정 가능
        targetSeries.setIsActive(false); //활성 상태
        targetVote.setStatus(Vote.VoteStatus.SERIES_QUIT); //투표에 할당
        seriesRepo.save(targetSeries);
        voteRepo.save(targetVote);
        }
        return voteResponse(seriesId, targetVote);
    }



    //1차 투표 결과
    public VoteResponse.VoteAttendResponse getStartVote(Long voteId){
        Member member = memberService.getMemberByToken();
        long memberId = member.getId();


        Vote targetVote = voteRepo.findById(voteId).orElseThrow(()->new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND));
        Series targetSeries = seriesRepo.findByVotes_Id(voteId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));
        //Long seriesId = targetSeries.getId();

        //예외: 투표를 개설한 본인이 아닙니다 //TODO 토큰
        if(targetSeries.getMember().getId() != memberId){
            //투표를 개설한 본인이 아닙니다
            throw new BusinessLogicException(ExceptionCode.NOT_HAVE_VOTE_AUTHORITY);
        }

        if(targetVote.getVoteCount()==1 && targetVote.getVoteResult()){ //이미 최대 투표를 했어요!
            //투표 종료에 대한 권한이 없습니다
            throw new BusinessLogicException(ExceptionCode.VOTE_ALREADY_GRADUATE);
        } //이 예외가 존재하는 이유는 해당 get을 통해 졸업을 할지말지 또 api를 호출하기 때문 !!!

        //예외: 투표의 총 횟수를 다 씀 (voteCount==2 여기서 거른다) //TODO 재투표를 다시 할 시리즈가 아니라고 ㅋㅋㅋ
        if(targetVote.getVoteCount()!=1){ //이미 최대 투표를 했어요!
            //투표 종료에 대한 권한이 없습니다
            throw new BusinessLogicException(ExceptionCode.VOTE_ALL_GRADUATE);
        } //이 예외가 존재하는 이유는 해당 get을 통해 졸업을 할지말지 또 api를 호출하기 때문 !!!

        //예외: 투표를 개설하지 않았습니다
        if (targetVote.getVoteCount()==0) {
            throw new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND);
        }

        //예외: 최초투표를 진행함, 최초투표에서 찬성 결과가 나옴 (voteCount==1 && voteResult==true)
        if(targetVote.getVoteResult()){
            //이 투표는 이미 졸업했어요!
            throw new BusinessLogicException(ExceptionCode.THIS_VOTE_RESULT_IS_TRUE);
        }

        return VoteResponse.VoteAttendResponse.of(
                targetSeries.getId(),
                voteId,
                targetVote.getAgree(),
                targetVote.getDisagree()
        );

    }

    //투표 개별조회
    public VoteResponse getVoteInfo(Long voteId){
        Member member = memberService.getMemberByToken();
        long memberId = member.getId();

        Vote targetVote = voteRepo.findById(voteId).orElseThrow(()->new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND));
        Series targetSeries = seriesRepo.findByVotes_Id(voteId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));
        Long seriesId = targetSeries.getId();

        Boolean isVotedMember = voteMemberRepo.existsByMember_IdAndVote_Id(memberId, voteId);


        return voteCountAddResponse(seriesId, targetVote, isVotedMember);
    }


        //내부 로직: 투표 횟수별 검증 메소드 (투표 전체 정보)
    public VoteResponse voteResponse(Long seriesId, Vote targetVote) { //voteCountAddResponse
            return VoteResponse.VoteDefaultResponse.of(
                    seriesId,
                    targetVote.getId(),
                    targetVote.getVoteCount(),
                    targetVote.getVoteResult(),
                    targetVote.getAgree(),
                    targetVote.getDisagree(),
                    targetVote.getStatus(),
                    targetVote.getVoteCreatedAt(),
                    targetVote.getVoteEndAt()
            );
    }

    //response: isVotedMember 구별용
    public VoteResponse voteCountAddResponse(Long seriesId, Vote targetVote, Boolean isVotedMember) {
            return VoteResponse.FirstVoteAddResponse.of(
                    seriesId,
                    targetVote.getId(),
                    targetVote.getVoteCount(),
                    targetVote.getVoteResult(),
                    targetVote.getAgree(),
                    targetVote.getDisagree(),
                    targetVote.getStatus(),
                    targetVote.getVoteCreatedAt(),
                    targetVote.getVoteEndAt(),
                    isVotedMember
            );
    }


        //로직: 마감기한 지났는지의 여부
    private Boolean isVotedNotEntAt(Vote targetVote){ //마감기한 안지남: true
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.isBefore(targetVote.getVoteEndAt());
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

    public void revoteException(Long seriesId){
        //TO완료DO (1) 해당 voteId 가져오기
        Vote firstVote = voteRepo.findBySeries_Id(seriesId).orElseThrow((()->new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND)));


        //TO완료DO (2) 마감기간이 지나지 않았으면 투표를 생성할 수 없음 예외
        if(isVotedNotEntAt(firstVote)){
            throw new BusinessLogicException(ExceptionCode.DEADLINE_FALL_SHORT);
        }

        //TODO (3) 최초투표의 결과가 조회되지 않았어요 > @@투표하기 만든 이후에 다시 검증
        if(firstVote.getVoteCount()==1 && firstVote.getVoteResult() == null){
        throw new BusinessLogicException(ExceptionCode.VOTE_RESULT_NOT_UPDATE);

        //TODO (4) 데이로그 안 만들었으면 터지는 에러

        //TODO (X) 재투표시에 memberVote 초기화: 필요 없음

       }
    }
}
