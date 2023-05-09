package com.ssts.ssts.domain.series.controller;

import com.ssts.ssts.domain.series.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series/votes")
@RequiredArgsConstructor //생성자 주입 해주는 public VoteController(Series Repo ... 이거해줌)
public class VoteController {
    private final VoteService voteService;



    //투표 요청 (voteStatus가 "투표중"이 명시되면, 찬성, 반대의 버튼을 보여줍니다)
    @PostMapping("/{series_id}")
    public ResponseEntity postVote(@PathVariable("series_id") Long seriesId){

        Object response = voteService.createVote(seriesId);

        return new ResponseEntity(response, HttpStatus.CREATED);

        //응답 response를 만들어야 할 것 같음 > 투표를 만드는데에 모든 series의 정보를 담을 필요는 없으니까
        //사실 만드는거라 크게 필요없을지도
    }

}
