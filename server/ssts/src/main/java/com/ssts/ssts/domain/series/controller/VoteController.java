package com.ssts.ssts.domain.series.controller;


import com.ssts.ssts.domain.series.response.vote.VoteResponse;
import com.ssts.ssts.domain.series.service.VoteService;
import com.ssts.ssts.global.utils.MultipleResponseDto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/series/votes")
@RequiredArgsConstructor //생성자 주입 해주는 public VoteController(Series Repo ... 이거해줌)
public class VoteController {
    private final VoteService voteService;



    //투표함 만들기 (voteStatus가 "투표중"이 명시되면, 찬성, 반대의 버튼을 보여줍니다)
    @PostMapping("/{series_id}")
    public ApiResponse<VoteResponse> postVote(@NotNull @PathVariable("series_id") Long seriesId){

        VoteResponse response = (VoteResponse) voteService.createVote(seriesId);

        return ApiResponse.create(response);
    }

    //@PatchMapping("/{series_id}/{votes}")
    //투표 하기 (사용자가 투표한 이후의 찬반 표수를 응답값으로 넘기면 좋을 것 같음)
    //@PutMapping("/{series_id}/{votes}/{member_id}")
    @PutMapping("/{series_id}/{votes}") //TODO 토큰시에 켜기
    //public ResponseEntity patchVote(@PathVariable("series_id") Long seriesId, @PathVariable("votes") @Max(1)int isAgree, @PathVariable("member_id")Long memberId){
    public ApiResponse<VoteResponse> patchVote(@NotNull @PathVariable("series_id") Long seriesId, @PathVariable("votes") @Max(1)int isAgree){ //TODO 토큰 테스트시에 주석 풀기
        //Excetption: isAgree의 값이 0과 1이 아닌 경우 Exception

        //Series response = voteService.attendVote(seriesId, isAgree);
        //VoteResponse response = (VoteResponse) voteService.attendVote(seriesId, isAgree, memberId);
        VoteResponse response = voteService.attendVote(seriesId, isAgree); //TODO 토큰시에 주석풀기

        return ApiResponse.ok(response);
    }


    //투표 종료
    //@PatchMapping("/quit/{series_id}/{member_id}")
    @PatchMapping("/graduation/{series_id}")
    //public ResponseEntity QuitVoteControl(@PathVariable("series_id") Long seriesId, @PathVariable("member_id") Long memberId, @PathParam("isQuit") Boolean isQuit){ //프론트가 boolean으로 보내야
    public ApiResponse<VoteResponse> QuitVoteControl(@NotNull @PathVariable("series_id") Long seriesId, @PathParam("isQuit") Boolean isQuit){

        //VoteResponse response = (VoteResponse) voteService.quitVote(seriesId, memberId, isQuit);
        VoteResponse response = (VoteResponse) voteService.quitVote(seriesId, isQuit);

        return ApiResponse.ok(response);
    }


    //1차 투표 결과 확인
    @GetMapping("/graduation/{series_id}")
    public ApiResponse<VoteResponse.VoteAttendResponse> getStartVote(@NotNull @PathVariable("series_id") Long seriesId){

        //VoteResponse response = (VoteResponse) voteService.quitVote(seriesId, memberId, isQuit);
        VoteResponse.VoteAttendResponse response = (VoteResponse.VoteAttendResponse) voteService.getStartVote(seriesId);

        return ApiResponse.ok(response);
    }

    //시리즈 투표 개별조회
    @GetMapping("{series_id}")
    public ApiResponse getVoteInfo(@NotNull @PathVariable("series_id") Long seriesId){
        VoteResponse response = (VoteResponse) voteService.getVoteInfo(seriesId);
        return ApiResponse.ok(response);
    }

}
