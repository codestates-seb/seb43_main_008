package com.ssts.ssts.domain.vote.response;

//package com.ssts.ssts.domain.series.response.vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    Long seriesId;
//    Series.VoteStatus voteStatus;
//    LocalDateTime voteEndAt; //투표가 언제 시작했는지는 그닥 중요하지 않음, 언제 마감됐느냐가 로직에서 제일 필요한 존재가 될 거임 (아마)
//
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class FirstVoteResponse extends VoteResponse { //첫 투표 Response
//
//        int voteCount;
//        Boolean voteResult;
//        int voteAgree;
//        int voteDisagree;
//        LocalDateTime voteCreatedAt;
//
//
//
//        //최초 투표일 때의 투표 Response 변경
//        public static FirstVoteResponse of(Long seriesId, int voteCount, Boolean voteResult, int voteAgree,
//                                           int voteDisagree, Series.VoteStatus voteStatus, LocalDateTime voteCreatedAt, LocalDateTime voteEndAt) {
//            VoteResponse.FirstVoteResponse vote = new FirstVoteResponse();
//
//            vote.setSeriesId(seriesId);
//            vote.setVoteCount(voteCount);
//            vote.setVoteResult(voteResult);
//            vote.setVoteAgree(voteAgree);
//            vote.setVoteDisagree(voteDisagree);
//            vote.setVoteStatus(voteStatus);
//            vote.setVoteCreatedAt(voteCreatedAt);
//            vote.setVoteEndAt(voteEndAt);
//            return vote;
//        }
//    }
//
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class RevoteResponse extends VoteResponse { //재투표 Response
//
//        int voteCount;
//        Boolean revoteResult;
//        int revoteAgree;
//        int revoteDisagree;
//        LocalDateTime voteCreatedAt;
//
//        public static RevoteResponse of(Long seriesId, int voteCount, Boolean revoteResult, int revoteAgree,
//                                        int revoteDisagree, Series.VoteStatus voteStatus, LocalDateTime voteCreatedAt, LocalDateTime voteEndAt) {
//            VoteResponse.RevoteResponse revote = new RevoteResponse();
//
//
//            revote.setSeriesId(seriesId);
//            revote.setVoteCount(voteCount);
//            revote.setRevoteResult(revoteResult);
//            revote.setRevoteAgree(revoteAgree);
//            revote.setRevoteDisagree(revoteDisagree);
//            revote.setVoteStatus(voteStatus);
//            revote.setVoteCreatedAt(voteCreatedAt);
//            revote.setVoteEndAt(voteEndAt);
//            return revote;
//        }
//    }
//
//    @NoArgsConstructor
//    @Setter
//    @Getter
//    @AllArgsConstructor
//    public static class VoteAttendResponse extends VoteResponse{ //최초 투표: 투표 참여 response
//
//        int voteAgree;
//        int voteDisagree;
//
//        @JsonIgnore
//        Series.VoteStatus voteStatus;
//
//        @JsonIgnore
//        LocalDateTime voteEndAt;
//
//        public static VoteAttendResponse of( Long seriesId, int voteAgree, int voteDisagree) {
//            VoteResponse.VoteAttendResponse firstAttendResponse = new VoteAttendResponse();
//
//            firstAttendResponse.setSeriesId(seriesId);
//            firstAttendResponse.setVoteAgree(voteAgree);
//            firstAttendResponse.setVoteDisagree(voteDisagree);
//
//            return firstAttendResponse;
//        }
//
//    }
//
//    //voteGet API Response
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class FirstVoteAddResponse extends VoteResponse { //첫 투표 Response
//
//        int voteCount;
//        Boolean voteResult;
//        int voteAgree;
//        int voteDisagree;
//        LocalDateTime voteCreatedAt;
//        Boolean isVotedMember;
//
//
//
//        //최초 투표일 때의 투표 Response 변경
//        public static FirstVoteAddResponse of(Long seriesId, int voteCount, Boolean voteResult, int voteAgree,
//                                           int voteDisagree, Series.VoteStatus voteStatus, LocalDateTime voteCreatedAt, LocalDateTime voteEndAt,
//                                           Boolean isVotedMember) {
//            VoteResponse.FirstVoteAddResponse vote = new FirstVoteAddResponse();
//
//            vote.setSeriesId(seriesId);
//            vote.setVoteCount(voteCount);
//            vote.setVoteResult(voteResult);
//            vote.setVoteAgree(voteAgree);
//            vote.setVoteDisagree(voteDisagree);
//            vote.setVoteStatus(voteStatus);
//            vote.setVoteCreatedAt(voteCreatedAt);
//            vote.setVoteEndAt(voteEndAt);
//            vote.setIsVotedMember(isVotedMember);
//            return vote;
//        }
//    }
//
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class RevoteAddResponse extends VoteResponse { //재투표 Response
//
//        int voteCount;
//        Boolean revoteResult;
//        int revoteAgree;
//        int revoteDisagree;
//        LocalDateTime voteCreatedAt;
//        Boolean isVotedMember;
//
//        public static RevoteAddResponse of(Long seriesId, int voteCount, Boolean revoteResult, int revoteAgree,
//                                        int revoteDisagree, Series.VoteStatus voteStatus, LocalDateTime voteCreatedAt, LocalDateTime voteEndAt, Boolean isVotedMember) {
//            VoteResponse.RevoteAddResponse revote = new RevoteAddResponse();
//
//
//            revote.setSeriesId(seriesId);
//            revote.setVoteCount(voteCount);
//            revote.setRevoteResult(revoteResult);
//            revote.setRevoteAgree(revoteAgree);
//            revote.setRevoteDisagree(revoteDisagree);
//            revote.setVoteStatus(voteStatus);
//            revote.setVoteCreatedAt(voteCreatedAt);
//            revote.setVoteEndAt(voteEndAt);
//            revote.setIsVotedMember(isVotedMember);
//            return revote;
//        }
//    }

}