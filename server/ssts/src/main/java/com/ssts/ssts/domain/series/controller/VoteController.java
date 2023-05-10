package com.ssts.ssts.domain.series.controller;

import com.ssts.ssts.domain.series.response.vote.VoteResponse;
import com.ssts.ssts.domain.series.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series/votes")
@RequiredArgsConstructor //생성자 주입 해주는 public VoteController(Series Repo ... 이거해줌)
public class VoteController {
    private final VoteService voteService;



    //투표함 만들기 (voteStatus가 "투표중"이 명시되면, 찬성, 반대의 버튼을 보여줍니다)
    @PostMapping("/{series_id}")
    public ResponseEntity postVote(@PathVariable("series_id") Long seriesId){

        VoteResponse response = (VoteResponse) voteService.createVote(seriesId);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }



}
