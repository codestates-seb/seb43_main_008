package com.ssts.ssts.domain.vote.response;

//package com.ssts.ssts.domain.series.response.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.vote.entity.Vote;
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
    Vote.VoteStatus voteStatus;
    LocalDateTime voteEndAt; //투표가 언제 시작했는지는 그닥 중요하지 않음, 언제 마감됐느냐가 로직에서 제일 필요한 존재가 될 거임 (아마)


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VoteDefaultResponse extends VoteResponse { //FirstVoteResponse

        Long voteId;
        int voteCount;
        Boolean voteResult;
        int voteAgree;
        int voteDisagree;
        LocalDateTime voteCreatedAt;



        //최초 투표일 때의 투표 Response 변경
        public static VoteDefaultResponse of(Long seriesId,Long voteId, int voteCount, Boolean voteResult, int voteAgree,
                                           int voteDisagree, Vote.VoteStatus voteStatus, LocalDateTime voteCreatedAt, LocalDateTime voteEndAt) {
            VoteResponse.VoteDefaultResponse vote = new VoteDefaultResponse();

            vote.setSeriesId(seriesId);
            vote.setVoteId(voteId);
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

    @NoArgsConstructor
    @Setter
    @Getter
    @AllArgsConstructor
    public static class VoteAttendResponse extends VoteResponse{ //최초 투표: 투표 참여 response

        int voteAgree;
        int voteDisagree;
        long voteId;

        @JsonIgnore
        Vote.VoteStatus voteStatus;

        @JsonIgnore
        LocalDateTime voteEndAt;

        public static VoteAttendResponse of( Long seriesId, Long voteId, int voteAgree, int voteDisagree) {
            VoteResponse.VoteAttendResponse firstAttendResponse = new VoteAttendResponse();

            firstAttendResponse.setSeriesId(seriesId);
            firstAttendResponse.setVoteId(voteId);
            firstAttendResponse.setVoteAgree(voteAgree);
            firstAttendResponse.setVoteDisagree(voteDisagree);


            return firstAttendResponse;
        }

    }
//
    //voteGet API Response
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FirstVoteAddResponse extends VoteResponse { //첫 투표 Response

        int voteCount;
        Boolean voteResult;
        int voteAgree;
        int voteDisagree;
        LocalDateTime voteCreatedAt;
        Boolean isVotedMember;
        Long voteId;



        //최초 투표일 때의 투표 Response 변경
        public static FirstVoteAddResponse of(Long seriesId, Long voteId, int voteCount, Boolean voteResult, int voteAgree,
                                           int voteDisagree, Vote.VoteStatus voteStatus, LocalDateTime voteCreatedAt, LocalDateTime voteEndAt,
                                           Boolean isVotedMember) {
            VoteResponse.FirstVoteAddResponse vote = new FirstVoteAddResponse();

            vote.setSeriesId(seriesId);
            vote.setVoteId(voteId);
            vote.setVoteCount(voteCount);
            vote.setVoteResult(voteResult);
            vote.setVoteAgree(voteAgree);
            vote.setVoteDisagree(voteDisagree);
            vote.setVoteStatus(voteStatus);
            vote.setVoteCreatedAt(voteCreatedAt);
            vote.setVoteEndAt(voteEndAt);
            vote.setIsVotedMember(isVotedMember);
            return vote;
        }
    }
}