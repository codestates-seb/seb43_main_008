package com.ssts.ssts.domain.series.response.vote;

//package com.ssts.ssts.domain.series.response.vote;

import com.ssts.ssts.domain.series.entity.Series;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VoteResponse<T> {

    //public static FirstVoteResponse ReVoteResponse;
    Long seriesId;
    Series.VoteStatus voteStatus;
    LocalDateTime voteCreatedAt;
    LocalDateTime voteEndAt;



    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FirstVoteResponse extends VoteResponse {

        int voteCount;
        Boolean voteResult;
        int voteAgree;
        int voteDisagree;


        public static FirstVoteResponse of(Long seriesId, int voteCount, Boolean voteResult, int voteAgree,
                                           int voteDisagree, Series.VoteStatus voteStatus, LocalDateTime voteCreatedAt, LocalDateTime voteEndAt) {
            VoteResponse.FirstVoteResponse vote = new FirstVoteResponse();

            vote.setSeriesId(seriesId);
            vote.setVoteCount(voteCount);
            vote.setVoteResult(voteResult);
            vote.setVoteAgree(voteAgree);
            vote.setVoteDisagree(voteDisagree);
            vote.setVoteStatus(voteStatus);
            vote.setVoteCreatedAt(voteCreatedAt);
            vote.setVoteEndAt(voteEndAt);
            return vote;
        }
    }




        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class RevoteResponse extends VoteResponse{

            int revoteCount;
            Boolean reVoteResult;
            int reVoteAgree;
            int reVoteDisagree;

            public static RevoteResponse of(Long seriesId, int revoteCount, Boolean revoteResult, int revoteAgree,
                                            int revoteDisagree, Series.VoteStatus voteStatus, LocalDateTime voteCreatedAt, LocalDateTime voteEndAt){
                VoteResponse.RevoteResponse revote = new RevoteResponse();


                revote. setSeriesId(seriesId);
                revote.setRevoteCount(revoteCount);
                revote.setReVoteResult(revoteResult);
                revote.setReVoteAgree(revoteAgree);
                revote.setReVoteDisagree(revoteDisagree);
                revote.setVoteStatus(voteStatus);
                revote.setVoteCreatedAt(voteCreatedAt);
                revote.setVoteEndAt(voteEndAt);
                return revote;
            }


        }
    }

//
//    @Getter
//    @AllArgsConstructor
//    public static class ReVoteResponse{
//        Long id;
//        int voteCount;
//        Boolean voteResult;
//        int reVoteAgree;
//        int reVoteDisagree;
//        Series.VoteStatus voteStatus;
//        LocalDateTime voteCreatedAt;
//        LocalDateTime voteEndAt;
//    }







//responseDto를 3개 만들기
//voteCount =1 이면 최초투표 (voteResult)
//voteCount = 2이면 재투표 (revoteResult), voteCreateAt과 voteEntAt은 초기화하여 사용합니다
//else 투표가 불가능합니다 예와

//이거 회원가입 ? ur클래스에 dto 하나에 몰아서 넣는 깃허브 참고해서 짜기

//    private Long id;
//    private int voteCount;
//
//    private Boolean voteResult;
//    private int voteAgree;
//    private int voteDisagree;
//
//
//    private Boolean revoteResult;
//    private int revoteAgree;
//    private int revoteDisagree;
//
//    private Series.VoteStatus seriesStatus;
//
//    private Boolean isPublic;
//    private Boolean isEditable;
//    private Boolean isActive;
//
//
//    private LocalDateTime voteCreatedAt;
//    private LocalDateTime voteEndAt;
