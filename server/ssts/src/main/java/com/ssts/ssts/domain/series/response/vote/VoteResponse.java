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
    LocalDateTime voteEndAt; //투표가 언제 시작했는지는 그닥 중요하지 않음, 언제 마감됐느냐가 로직에서 제일 필요한 존재가 될 거임 (아마)


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FirstVoteResponse extends VoteResponse { //첫 투표 Response

        int voteCount;
        Boolean voteResult;
        int voteAgree;
        int voteDisagree;
        LocalDateTime voteCreatedAt;


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
    public static class RevoteResponse extends VoteResponse { //재투표 Response

        int revoteCount;
        Boolean reVoteResult;
        int reVoteAgree;
        int reVoteDisagree;
        LocalDateTime voteCreatedAt;

        public static RevoteResponse of(Long seriesId, int revoteCount, Boolean revoteResult, int revoteAgree,
                                        int revoteDisagree, Series.VoteStatus voteStatus, LocalDateTime voteCreatedAt, LocalDateTime voteEndAt) {
            VoteResponse.RevoteResponse revote = new RevoteResponse();


            revote.getSeriesId();
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
