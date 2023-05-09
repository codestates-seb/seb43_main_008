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
public class VoteResponse {

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
        //강제 형변환을 진행하면 완전히 잃어버림 (상속받은 상위클래스)

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
