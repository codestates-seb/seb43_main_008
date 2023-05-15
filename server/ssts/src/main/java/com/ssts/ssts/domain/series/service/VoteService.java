package com.ssts.ssts.domain.series.service;

/*
* 해당 시리즈에 나갔다가 들어온 사용자는 투표가 아니라 투표 현황을 볼 수 있도록, 해당 memberVote의 확인
ㄴ> 해당 사용자가 특정 시리즈에 접속했을 때, 그 시리즈에 대한 투표 여부 !
* ㄴ> response에 따로 담아서 보내기 seriseResponse 만지기
* */

//하늘언니껄로 back 새 브랜치 파기
//브랜치 파서 ~ 변경 사항 전부 적용하기 (여기거 거기로)
// 그 다음에 마저 진행하기

import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.member.entity.MemberVote;
import com.ssts.ssts.domain.member.repository.MemberRepository;

import com.ssts.ssts.domain.member.repository.MemberVoteRepository;
import com.ssts.ssts.domain.member.service.MemberService;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.series.repository.SeriesRepository;
import com.ssts.ssts.domain.series.response.vote.VoteResponse;
import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import com.ssts.ssts.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//시간 계산 import
import java.time.*;
import java.util.Date;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final SeriesRepository seriesRepo;
    private final MemberVoteRepository voteMemberRepo;
    private final MemberService memberService;
    //private final SeriesRepository seriesRepo;
    private final MemberRepository memberRepo;

    //할 일
    //[투표:ETC]
    //@SeriseGet(id):votedMember 여부 담아주기 (대현님 SeriseResponse, SeriseService 침범하기)

    //[투표생성]
    //@(db) 재투표를 만들 시에 해당 seriesId를 가지고 있는 memberVote 삭제 (초기화) -> 조건 걸면 될 것 같음
    //@ (예외)투표를 만드려고 할때, 총 투표 수가 2개면 더이상의 투표를 만들 수 없음 -> 만드는 요청을 보냈을 때, votecount==2 -> Exception
    //@ 마감기한이 지나지 않은 상태로 투표를 생성시 예외 @

    //[투표하기]
    //@ (예외) 존재하지 않는 시리즈입니다 -> 대현님이 따로 코드 안 짜서 나중에 합칠 때에는 바꿔주어야 함
    //@ (예외)투표가 존재하지 않습니다 -> 웅니가 만든 거 보기 @ voteCount로 검증
    //@ (예외)이미 투표한 게시글 입니다 / 시리즈 각각 독립적으로 돌아야 함 @

    //[투표 종료하기]-----------------------------------------------------------------------------------------------------
    //1. 투표 종료시에 활성 상태가 비활성으로 바뀌어야 한다 && VOTE_STATUS 도 투표 종료로 값을 바꾸어야 한다
    //ㄴ> 비활성 경우 (1) voteCount==2 / (2) voteCount==1, voteResult == true
    //2. is_editable: true로 바꾸기 / if(voteCount==1 && voteResult ==false) 경우는 false로 둔다 / 그냥 1번에 is_editable 넣으면 else로 나머지 해결
    //3. response
    //ㄴ> if(voteCount ==1 && voteResult==true) => voteAgree, voteDisagree, voteResult?
    //ㄴ> if(voteCount ==2) => revoteAgree, revoteDisagree, revoteResult?

    //[투표 종료하기: 예외]
    //존재하지 않는 시리즈 입니다
    //투표를 종료할 수 없는 사용자 입니다

    //[투표 종료에 대한 세분화]
    //종료에대해 상황세분화가 안되어있음
    //자동종료 -> 시리즈 조회했을 때 2일 지난 사람들만
    //1. 의지 없는 사람
    //2. 의지가 있는 사람

    //자동종료는 시간 종료됐을 때, 조회할 때마다 시간 로직이 들어가서 조회시 시간 지나면 자동종료 되도록 구현하기

    //진행 상황을 정하고 그 상황에 대해 상태값을 받는 게 이거임

    //상황자체를 분리해서 종료작업 / 종료 이후의 작업에대한 경우의수를 늘려야됨(각각ㄷ의 요청으로)

    //결론: 종료의 경우가 나뉘어져있다
    //종료이후:버릴지, 재활용할지 (여기서는 파라미터로 Boolean 버릴지말지 값을 받아올 수 있음)
    //종료: 그냥 투표를 종료해버리는 경우
    //(자동)종료: default: 2일 지나면 종료되는 게시글 조회 시 자동종료 (시간)

    //[투표: 자동종료하기]
    //ㄴ> 시리즈 개별조회 침법 필요
    //ㄴ> 개별이 조회할 때마다, 시간 계산 로직을 돌려서, 2일이 지났으면 !!!! 그때 자동으로 => (setEditable)+(setActive)+(setSeriesStatus) 바꾸기
    //------------------------------------------------------------------------------------------------------------------

    //Entity에 한국 시간 하기

    //[공통]
    // 예외 제대로 안 걸린 부분 예외 걸어주기

    //생각: memberVote의 초기화는 이루어져야하는가? -> voteCount ==2가 되면, 더이상의 투표를 진행할 필요가 없어서 투표 생성시 voteCount==1 인 경우 외에는 딱히. 임



    //투표 생성
    @Transactional
    public Object createVote(Long seriesId) {
        //public VoteResponse createVote(Long seriesId) {

        Series targetSeries = seriesRepo.findById(seriesId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS)); //투표를 생성할 Series Entity 찾기

        //투표에 따른 상태값 변경 //of를 쓴 게 아닌데 일단은 냅 두기 / 리팩토링 대상
        //(voteCount>2) 더이상 투표를 개설할 수 없습니다 예외 만들기
        if (targetSeries.getVoteCount()==2){ throw new BusinessLogicException(ExceptionCode.CAN_NOT_MAKE_VOTE); }

        //마감기간이 지나지 않았으면 투표를 생성할 수 없음 예외 만들기 / 재투표일때만 예외가 걸림 (최초투표는 계산할 게 없으니까)
        if(targetSeries.getVoteCount()==1){ //count==1을 먼저 검사하지 않으면 마감 기한이 없을 때 NullPointer 뜸 (&& 불가)
            //(1트) localDateTime을 jpa가 지원하도록 Date 타입으로 변환 /jpa에서는 localDateTime을 지원하지 않아서 시간 지원하는 Date 타입으로 변환
            //LocalDateTime changeVoteTime = targetSeries.getVoteEndAt();
            //Date targetVoteEndTime = Date.from(changeVoteTime.atZone(ZoneId.systemDefault()).toInstant()); -> 변환에러

            //(2트)
            //LocalDateTime localDateTime = targetSeries.getVoteEndAt();
            //Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
            //Date targetVoteEndTime = Date.from(instant); -> 변환 과정에서 난리나서 버림

            //(3트) 먀감기간이 지나지 않은 투표 생성시 예외 (서비스 로직에서 시간 변환)
//            ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul")); //한국 기준으로 현재 시간 얻기 (LocalDateTIme 객체 반환)
//            if (currentTime.isBefore(targetSeries.getVoteEndAt().atZone(ZoneId.of("Asia/Seoul")))) {
//                throw new BusinessLogicException(ExceptionCode.DEADLINE_FALL_SHORT);
//            }

            //(4트) .global.config().LocaleConfig 에서 LocalTimeZone을 서울 시간으로 바꿔서 따로 바꿀 필요가 없음
//            LocalDateTime currentTime = LocalDateTime.now();
//            if(currentTime.isBefore(targetSeries.getVoteEndAt())){
//                throw new BusinessLogicException(ExceptionCode.DEADLINE_FALL_SHORT);
//            }

            //(5트) 마감기간 지나지 않은 것에 대한 메소드 2번 사용, 외부 메소드 처리
            if(isVotedNotEntAt(targetSeries)){
                throw new BusinessLogicException(ExceptionCode.DEADLINE_FALL_SHORT);
            }

        }

        //투표에 따른 상태값 변경 //of를 쓴 게 아닌데 일단은 냅 두기 / 리팩토링 대상
        targetSeries.setPublic(true); //시리즈 공개
        targetSeries.setEditable(false); //타이틀 수정 불가
        targetSeries.setActive(true); //활성 상태
        targetSeries.setSeriesStatus(Series.VoteStatus.SERIES_SLEEP); //투표중 할당
        targetSeries.setVoteCount(targetSeries.getVoteCount() + 1); //최초투표이든, 아니든 +1 //투표함을 만들 때, voteCount가 증가

        //투표 생성시간 할당
        targetSeries.setVoteCreatedAt(LocalDateTime.now());
        //투표 마감기간 (2일) 할당
        //targetSeries.setVoteEndAt(targetSeries.getVoteCreatedAt().plusDays(2));
        targetSeries.setVoteEndAt(targetSeries.getVoteCreatedAt().plusSeconds(15));
        //ㄴ> 테스트 마감기간: 15초

//        if(targetSeries.getVoteCount() == 2){
//            voteMemberRepo.deleteAllBySeries_Id(seriesId);
//        }

        //재투표시에 memberVote 초기화 (중복 제거)
        if (targetSeries.getVoteCount() == 2) { voteMemberRepo.deleteAllBySeries_Id(seriesId); }


        seriesRepo.save(targetSeries);
        return voteCountResponse(seriesId, targetSeries);
    }

//----------------------------------------------------------------------------------------
    /* CHECK LIST : EXCEPTION
        //1. 같은 시리즈에 동일 인간이 투표하면 중복 걸리는가 ? ㅇㅇㅇㅇ
        //2. 다른 사람이 동일 시리즈에 투표할 수 있는가? ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
        //3. 동일 사람이 다른 시리즈의 투표를 진행할 수 있는가? ㄴㄴㄴㄴ -> ㅇㅇㅇㅇ
        //ㄴ> 투표를 할 때, 시리즈의 아이디가 다르면 투표할 수 있는 로직이 되어야 함 -> 이게 2번인데 뭐가 문제지?
*///----------------------------------------------------------------------------------------


    //투표 하기----------------------------------------------------------------------------------------
    @Transactional
    public VoteResponse attendVote(Long seriesId, int isAgree){ //TODO 토큰 테스트시에 주석 풀기
    //public Object attendVote(Long seriesId, int isAgree, Long memberId) { //@@토큰 로직@@

        if(isAgree < 0 || isAgree > 1){throw new BusinessLogicException(ExceptionCode.CAN_NOT_VOTE_VALUE);}

        //[투표하기: 예외 처리]
        //이거 근데 응답값 안주는거면 반환값 필요없잖아 <<<<<< !!!!! 고민해보기 !!!!!!

        //ㄴ> 제약사항: 중복 투표가 가능한지 ?
        //seriesId를 통해 vote의 정보를 불러와야 한다
        //isAgree를 통해 response의 값을 넣어준다(setMethod)
        //ㄴ> attendVoteResponse 해야할듯
        //값을 반환한다 (seriesId, voteAgree, voteDisAgree, voteEndTime) / voteEndTime은 혹시 모르니까 계속 담는 게 좋겠다
        //ㄴ> 프론트에게 보여주는 값은 3개지만, series에 있는 vote의 값들이 수정되어야 한다

        //토큰 값을 받아와야하는 로직은 아직 미구현
        //임시값을 넣어서 구현해놇기 !!!!!!!!!!!!!!

        //1. memberVote 만들어서 중복 체크 할 erd 구현
        //2. 이제 투표하면 해당 멤버를 memberVote에 저장하도
        //이제 하늘이 갈라져도 3번은 투표한 놈임

        //Entity 구현 매니투원 지웟고, 이제 투표 하면 이제 memberVote에 값이 저장됨
        // 예외: voteMemberRepo에 해당 멤버가 있을 경우
        //예외 코드 만들어서 글로 빼고 시작화기
        //위에서부터 쭉 내리니까 if() => Exception 형식으로 진행하기

        //TODO *-토큰Id적용--* TODO토큰시에 주석풀기
        long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));


        //[1. 시리즈가 존재하지 않습니다.]-----------------------------------
        //Series targetSeries = findSeriesById(seriesId).orElseThrow(); //시리즈 Id를 통해 Series 객체 꺼내기
        Series targetSeries = seriesRepo.findById(seriesId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        //ㄴ> 응답값을 만들기 위해 존재
        int voteCount = targetSeries.getVoteCount();

        //투표가 존재하지 않습니다
        //시리즈 Id를 가진 memberVote의 Id 가져오면 안됨 -> 멤버가 투표 안하면 안뜸
        //serise의 voteCount가 0이면 예외를 터트려야 함 (나는 Vote테이블 분리가 아니라서 보트의 Id가 없음)
        if(voteCount == 0){throw new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND);}
        //ㄴ> 얘는 시리즈 받은 이후에만 할 수 있어서 테스트 못ㅎ팜

        //Series targetSeries = seriesRepo.findById(seriesId).orElseThrow(()->new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND));
        // Member member = memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));


        //[2. 동일 시리즈에, 2번 투표하면 투표 중복 에러]-----------------------------------
        //db에 현재 아이디가 존재하는지에 대한 로직
        //Optional<Series> dbSeries = seriesRepo.findById(seriesId);
        //[예외] [//안돌아서 디버깅 돌려야함 1!!!!!!!!!!]
        //if 비즈니스 로직 throw new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS);
        //해당 시리즈에 투표한 이력이 있는지 볼 거임, 나는 일단 맵핑이 안되어있어서 레포 이동 짱많이해야함
        //Optional<Long> checkDBMember = voteMemberRepo.findByMember_id(memberId); //멤버 아이디를 받아왔을 때, 얘가 있으면 예외
        //ㄴ> null값이 들어올 수 있으므로 Optional 이여야 함
        //비교대상: (dbMember:checkDBMember) vs (외부에서 받아오는 memberId)

//폐기
//        //if 해당 repo의 시리즈Id일때, (path로 받아온 시리즈id가 db에 존재할 때) if(findById(seriesId) / boolean
//        if (checkDBMember.isPresent() && dbSeries.isPresent()) { //만약 값이 존재한다면 중복 !
//            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_VOTE);
//            //ㄴ> 비즈니스 로직 하나에 여러가지 코드가 있는데 그걸 분류해줘야함
//        }
        //아 이거 예외가 좀 조건이 이상한 거 같음
        //해당 시리즈에서 !!!! 투표할 때, 같은 사람이면 << 이 두 조건이여야 함
        //같은 시리즈 안에서<<가 조건이여야지 isPrsent 면 안됨


        //[성공 !!!!!!!!!]
        //받아온 memberId를 가지고 memberVote를 검색했을 떄, memberVote의 리스트가 나온다
        //해당 memberVote의 리스트 중에서, 현재 series_id 가 존재하는지 검색한다
        //있으면 예외 !
        //ㄴ> jpa 다중 조건 걸 수 잇음 ????????? => [Spring] JPA Specification~~~~!!!!!1
        //ㄴ>repository 메소드 보기
        Boolean isVotedMember = voteMemberRepo.existsByMember_IdAndSeries_Id(memberId, seriesId); //true => false
        //ㄴ> 존재하지 않을 때 예외처리: .orElseThrow(IllegalArgumentException::new);
        //IllegalArgumentException : 잘못된 매개변수가 옴
        //ㄴ>existsBy: 찾아서 boolean 값 반환해줌
        //ㄴ> AND, OR 조건 가능
        if (isVotedMember) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_VOTE);
        }
        //ㄴ> 이게 성립하려면 같은 사용자가 다른 시리즈 2개에 투표할 수 있어야 하고, 같은 시리즈에 투표했을 때 예외 걸려야 함
        //ㄴ> db에 존재하지 않는 값이면 null 반환됨 ㅇㅇㅇㅇㅇ
        //----------------------------------------------------------------------------------------------------------------------


        //[투표하기: 투표 로직]
        //투표하는 member찾기, 시리즈는 targetSeries가 있음
        //Member voteMember = memberService.findMemberById(memberId); //다른 도메인의 레포지토리를 가는 게 아니라 서비스에 있는 메소드 재활용
        //터짐: 이거 이제 service가 없었다

        Member voteMember = memberRepo.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));;

        //최초투표
        if (targetSeries.getVoteCount() == 1) {
            //경우1: 최초 투표일 경우
            if (isAgree == 1) {//본래 있는 db의 컬럼을 바꿔줌
                targetSeries.setVoteAgree(targetSeries.getVoteAgree() + 1);
            } else if (isAgree == 0) {
                targetSeries.setVoteDisagree(targetSeries.getVoteDisagree() + 1);
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

            if (isAgree == 1) { //찬성
                targetSeries.setRevoteAgree(targetSeries.getRevoteAgree() + 1);
            } else if (isAgree == 0) { //반대
                targetSeries.setRevoteDisagree(targetSeries.getRevoteDisagree() + 1);
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



    //[투표 종료하기]
    //1. 투표 종료시에 활성 상태가 비활성으로 바뀌어야 한다
    //ㄴ> 비활성 경우 (1) voteCount==2 / (2) voteCount==1, voteResult == true
    //2. is_editable: true로 바꾸기 / if(voteCount==1 && voteResult ==false) 경우는 false로 둔다 / 그냥 1번에 is_editable 넣으면 else로 나머지 해결
    //3. response
    //ㄴ> if(voteCount ==1 && voteResult==true) => voteAgree, voteDisagree, voteResult?
    //ㄴ> if(voteCount ==2) => revoteAgree, revoteDisagree, revoteResult?

    //[투표 종료하기: 예외]
    //@ 존재하지 않는 시리즈 입니다
    //투표를 종료할 수 없는 사용자 입니다


    //종료에대해 상황세분화가 안되어있음
    //자동종료 -> 시리즈 조회했을 때 2일 지난 사람들만
    //1. 의지 없는 사람
    //2. 의지가 있는 사람

    //자동종료는 시간 종료됐을 때, 조회할 때마다 시간 로직이 들어가서 조회시 시간 지나면 자동종료 되도록 구현하기

    //진행 상황을 정하고 그 상황에 대해 상태값을 받는 게 이거임

    //상황자체를 분리해서 종료작업 / 종료 이후의 작업에대한 경우의수를 늘려야됨(각각ㄷ의 요청으로)

    //결론: 종료의 경우가 나뉘어져있다
    //종료이후:버릴지, 재활용할지 (여기서는 파라미터로 Boolean 버릴지말지 값을 받아올 수 있음)
    //종료: 그냥 투표를 종료해버리는 경우
    //종료: default: 2일 지나면 종료되는 게시글 조회 시 자동종료 (시간)



    //비정상 종료
    //1. 재투표의 기회가 1번 남았는데 버리고 싶어요 => 제가 드리고 상태값에 따라서, 아름 -> 새 페이지를 준다고 했음
    //ㄴ> 재투표이고, 1차투표인데, 과반수가 아니면 사용자에게 물어보고나서 종료
    //ㄴ> api => voteResult=false, revote=> X /

    //ㄴ> voteResult = false / revoteX => voteCount==1 /

    //2. 투표 2번을 돌았는데? 둘다 버리지 말래 => 메달 없이 자동 졸업 => 이거는 내가 알아서 하기로 함


    //(5/13)
    //근데 사실 종료가 3개가 아니라 2개 아닌가?
    //마감 2개 전부 하는 경우 => (getSerise)에서 처리
    //마감 1개 하고 재투표 없이 종료하는 경우
    //투표 도중에 강제중단 => 없는걸로 해

    //<비정상 종료>
    //비정상 종료의 기본값은 사용자가 종료할 때의 값하고 동일함
    //그렇기 때문에 1차투표를 한다고 골랐을 때,

    //(1차 투표 재시도 가능할 때)
    //수정가능 꺼짐 -> 강제종료시에 나 투표할게 ㅇㅇㅇ 하는 순간 열려야 함
    //isActive꺼짐 -> 위와동일

    //의 수정값이 들어감
    //비정상 졸업시에는 지금 시간 지난후의 마감값 그대로 가면 되어서 뭐 건들 게 없음

    @Transactional //[모든 예외를 거치고 남은 걸려져서 들어오는 값이 종료하기의 조건이 되도록]
    //public Series attendVote(Long seriesId, int isAgree){ [Boolean isQuit: 투표를 더 할지 말지 결정하는 값 (더 안한다:1) / (더 한다:0)]
    //public Object quitVote(Long seriesId,  Long memberId, Boolean isQuit){ //[마감 1개 하고 재투표 없이 종료하는 경우] => 새 페이지 처리
    public Object quitVote(Long seriesId, Boolean isQuit){ //TODO 토큰 적용시에 풀기

        //TODO *-토큰Id적용--* TODO토큰시에 주석풀기
        long memberId = SecurityUtil.getMemberId();
        memberRepo.findById(memberId).
                orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Series targetSeries = seriesRepo.findById(seriesId).orElseThrow(()->new BusinessLogicException(ExceptionCode.SERIES_NOT_EXISTS));

        //투표 재시도의 선택권이 없는 예외 거르기
        //예외: 투표를 개설한 본인이 아닙니다 //TODO 토큰
        if(targetSeries.getMember().getId() != memberId){
            //투표를 개설한 본인이 아닙니다
            //throw new BusinessLogicException(ExceptionCode.)
            throw new BusinessLogicException(ExceptionCode.NOT_HAVE_VOTE_AUTHORITY);
        }

        //예외: 투표를 개설하지 않았습니다
        if (targetSeries.getVoteCount()==0) {
            throw new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND);
        }

        //예외: 투표의 총 횟수를 다 씀
        if(targetSeries.getVoteCount()!=1){
            //투표 종료에 대한 권한이 없습니다
            throw new BusinessLogicException(ExceptionCode.NOT_HAVE_VOTE_AUTHORITY);
        }


        //[리팩토링 진입점: voteCount는 2부터 걸러지기 때문에, 1밖에 안들어옴. 조건에 1이 달릴 이유가 있나?]
        //if(targetSeries.getVoteCount()==1 && targetSeries.getVoteResult()){
        if(targetSeries.getVoteResult()==null){
            //투표 결과가 존재하지 않습니다
            throw new BusinessLogicException(ExceptionCode.VOTE_RESULT_IS_NOT_EXSIST);
        }


        //예외: 최초투표를 진행함, 최초투표에서 찬성 결과가 나옴 (voteCount==1 && voteResult==true)
        if(targetSeries.getVoteResult()){
            //이 투표는 이미 졸업했어요!
            throw new BusinessLogicException(ExceptionCode.THIS_VOTE_RESULT_IS_TRUE);
        }

        //예외: 최초투표를 진행햤는데, 마감기한이 지나지 않은 상태 (voteCount==1 && 마감기간이 지나지 않은 경우)
        if(isVotedNotEntAt(targetSeries)){
            //투표 종료에 대한 권한이 없습니다
            throw new BusinessLogicException(ExceptionCode.DEADLINE_FALL_SHORT);
        }

        //걸려지는 경우 (1) 투표를 더 한다고 선택 ( && voteCount==1)
        if(!isQuit){ //(isQuit==false)
            //voteCount가 1인 상태에서, 자동 마감 시간이 지나면, VoteStatus는 SERIES_QUIT 상태가 됨
            //voteService => quitVote에서 투표를 더 한다고 선택하지 않으면, 상태는 계속 SERISE_QUIT
            //quitVote에서 투표를 더 한다고 선택하면, VoteStatus: SERISE_ACTIVE 변경
            targetSeries.setEditable(true);
            targetSeries.setActive(true);
            targetSeries.setSeriesStatus(Series.VoteStatus.SERIES_ACTIVE);
        }

        //걸러지는 경우 (2) / 최종: 투표를 더 안할게요 voteCount==1&&voteResult==false => 로직이 도는 대상 (isQuit==1)
        else {
        targetSeries.setEditable(false); //타이틀 수정 가능
        targetSeries.setActive(false); //활성 상태
        targetSeries.setSeriesStatus(Series.VoteStatus.SERIES_QUIT); //투표에 할당
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
        }else {
            //throw new IllegalArgumentException("ㅇㅓ쩌구 ..");
            throw new BusinessLogicException(ExceptionCode.VOTE_NOT_FOUND);
        }

    }


    //객체 setter 해주는 메소드 짜기

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
