package com.ssts.ssts.domain.series.dto;


import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.series.entity.Series;
import com.ssts.ssts.domain.vote.entity.Vote;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
public class SeriesResponseDto {
    //seriseResponse
    private Long id;
    private String title;
    private String image;
    private int daylogCount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    private Vote.VoteStatus seriesStatus;

    private Boolean isPublic;
    private Boolean isEditable;
    private Boolean isActive;

    private int voteCount;
    private Boolean isVotedMember;
    private int totalVote;
    private Boolean isBookmarkedMember;


    //vote> 추가해야 한다고 생각함
    private long voteId;
    //voteResponse
    private int agree;
    private int disagree;
    private Boolean result;



    //이제 안 씀

//    private Boolean voteResult;
//    private int voteAgree;
//    private int voteDisagree;
//    private Boolean revoteResult;
//    private int revoteAgree;
//    private int revoteDisagree;





    public static SeriesResponseDto of(Long id, //얘가 아마 기본 response
                                       String title,
                                       String image,
                                       int daylogCount,
                                       LocalDateTime createdAt,
                                       LocalDateTime modifiedAt,
                                       int voteCount,

//                                       Boolean voteResult,
//                                       int voteAgree, int voteDisagree,
//                                       Boolean revoteResult,
//                                       int revoteAgree,
//                                       int revoteDisagree,

                                       Boolean result,
                                       int agree,
                                       int disagree,

                                       Vote.VoteStatus voteStatus,
                                       Boolean isPublic,
                                       Boolean isEditable,
                                       Boolean isActive,
                                       int totalVote) {
        SeriesResponseDto seriesResponseDto = new SeriesResponseDto();

        seriesResponseDto.setId(id);
        seriesResponseDto.setTitle(title);
        seriesResponseDto.setImage(image);
        seriesResponseDto.setDaylogCount(daylogCount);
        seriesResponseDto.setCreatedAt(createdAt);
        seriesResponseDto.setModifiedAt(modifiedAt);
        seriesResponseDto.setVoteCount(voteCount);

        seriesResponseDto.setSeriesStatus(voteStatus);
        seriesResponseDto.setIsPublic(isPublic);
        seriesResponseDto.setIsEditable(isEditable);
        seriesResponseDto.setIsActive(isActive);
        seriesResponseDto.setTotalVote(totalVote);


//        seriesResponseDto.setVoteResult(voteResult);
//        seriesResponseDto.setVoteAgree(voteAgree);
//        seriesResponseDto.setVoteDisagree(voteDisagree);
//        seriesResponseDto.setRevoteResult(revoteResult);
//        seriesResponseDto.setRevoteAgree(revoteAgree);
//        seriesResponseDto.setRevoteDisagree(revoteDisagree);
        return seriesResponseDto;
    }

    public static SeriesResponseDto of(Long id, //얘가 bookmarkedMember 존재하는 거고
                                       String title,
                                       String image,
                                       int daylogCount,
                                       LocalDateTime createdAt,
                                       LocalDateTime modifiedAt,
                                       int voteCount,

//                                       Boolean voteResult,
//                                       int voteAgree, int voteDisagree,
//                                       Boolean revoteResult,
//                                       int revoteAgree,
//                                       int revoteDisagree,

                                       Boolean result,
                                       int agree,
                                       int disagree,

                                       Vote.VoteStatus voteStatus,
                                       Boolean isPublic,
                                       Boolean isEditable,
                                       Boolean isActive,
                                       Boolean isVotedMember,
                                       Boolean isBookmarkedMember) {
        SeriesResponseDto seriesResponseDto = new SeriesResponseDto();

        seriesResponseDto.setId(id);
        seriesResponseDto.setTitle(title);
        seriesResponseDto.setImage(image);
        seriesResponseDto.setDaylogCount(daylogCount);
        seriesResponseDto.setCreatedAt(createdAt);
        seriesResponseDto.setModifiedAt(modifiedAt);
        seriesResponseDto.setVoteCount(voteCount);
        seriesResponseDto.setSeriesStatus(voteStatus);
        seriesResponseDto.setIsPublic(isPublic);
        seriesResponseDto.setIsEditable(isEditable);
        seriesResponseDto.setIsActive(isActive);
        seriesResponseDto.setIsVotedMember(isVotedMember);
        seriesResponseDto.setIsBookmarkedMember(isBookmarkedMember);


//        seriesResponseDto.setVoteResult(voteResult);
//        seriesResponseDto.setVoteAgree(voteAgree);
//        seriesResponseDto.setVoteDisagree(voteDisagree);
//        seriesResponseDto.setVoteResult(revoteResult);
//        seriesResponseDto.setRevoteAgree(revoteAgree);
//        seriesResponseDto.setRevoteDisagree(revoteDisagree);

        return seriesResponseDto;
    }



    //그 다음 오버어쩌구가.... 아 근데 일단 돌아가는 것만 확인하면 되니까 진짜 기본적인 거 하나만 만들자 //TODO TEST RESPONSE (VOTE 존재)
    public static SeriesResponseDto of(Long id, //얘가 bookmarkedMember 존재하는 거고
                                       String title,
                                       Boolean result,
                                       int agree,
                                       int disagree,

                                       Vote.VoteStatus voteStatus,
                                       Boolean isPublic,
                                       Boolean isEditable,
                                       Boolean isActive,
                                       Boolean isVotedMember
    ) {
        SeriesResponseDto seriesResponseDto = new SeriesResponseDto();

        seriesResponseDto.setId(id);
        seriesResponseDto.setTitle(title);
        seriesResponseDto.setSeriesStatus(voteStatus);
        seriesResponseDto.setIsPublic(isPublic);
        seriesResponseDto.setIsEditable(isEditable);
        seriesResponseDto.setIsActive(isActive);
        seriesResponseDto.setIsVotedMember(isVotedMember);

        return seriesResponseDto;
    }


    //TODO TEST RESPONSE (VOTE 미존재)
    public static SeriesResponseDto of(Long id,
                                       String title,
                                       int daylogCount,
                                       Boolean isPublic,
                                       Boolean isEditable,
                                       Boolean isActive//,
    ) {
        SeriesResponseDto seriesResponseDto = new SeriesResponseDto();

        seriesResponseDto.setId(id);
        seriesResponseDto.setTitle(title);
        seriesResponseDto.setIsPublic(isPublic);
        seriesResponseDto.setIsEditable(isEditable);
        seriesResponseDto.setIsActive(isActive);

        return seriesResponseDto;
    }


//    private Long id;
//
//    private String title;
//
//    private String image;
//
//    private int daylogCount;
//
//    private LocalDateTime createdAt;
//
//    private LocalDateTime modifiedAt;
//
//    private int voteCount;
//
//    private Boolean voteResult;
//
//    private int voteAgree;
//
//    private int voteDisagree;
//
//    private Boolean revoteResult;
//
//    private int revoteAgree;
//
//    private int revoteDisagree;
//
//    private Vote.VoteStatus seriesStatus;
//
//    private Boolean isPublic;
//
//    private Boolean isEditable;
//
//    private Boolean isActive;
//
//    private Boolean isVotedMember;
//
//    private int totalVote;
//
//    private Boolean isBookmarkedMember;
//
//
//
//
//    public static SeriesResponseDto of(Long id,
//                                       String title,
//                                       String image,
//                                       int daylogCount,
//                                       LocalDateTime createdAt,
//                                       LocalDateTime modifiedAt,
//                                       int voteCount, Boolean voteResult,
//                                       int voteAgree, int voteDisagree,
//                                       Boolean revoteResult,
//                                       int revoteAgree,
//                                       int revoteDisagree,
//                                       Vote.VoteStatus voteStatus,
//                                       Boolean isPublic,
//                                       Boolean isEditable,
//                                       Boolean isActive,
//                                       int totalVote) {
//        SeriesResponseDto seriesResponseDto = new SeriesResponseDto();
//
//        seriesResponseDto.setId(id);
//        seriesResponseDto.setTitle(title);
//        seriesResponseDto.setImage(image);
//        seriesResponseDto.setDaylogCount(daylogCount);
//        seriesResponseDto.setCreatedAt(createdAt);
//        seriesResponseDto.setModifiedAt(modifiedAt);
//        seriesResponseDto.setVoteCount(voteCount);
//        seriesResponseDto.setVoteResult(voteResult);
//        seriesResponseDto.setVoteAgree(voteAgree);
//        seriesResponseDto.setVoteDisagree(voteDisagree);
//        seriesResponseDto.setRevoteResult(revoteResult);
//        seriesResponseDto.setRevoteAgree(revoteAgree);
//        seriesResponseDto.setRevoteDisagree(revoteDisagree);
//        seriesResponseDto.setSeriesStatus(voteStatus);
//        seriesResponseDto.setIsPublic(isPublic);
//        seriesResponseDto.setIsEditable(isEditable);
//        seriesResponseDto.setIsActive(isActive);
//        seriesResponseDto.setTotalVote(totalVote);
//
//
//        return seriesResponseDto;
//    }
//
//    public static SeriesResponseDto of(Long id,
//                                       String title,
//                                       String image,
//                                       int daylogCount,
//                                       LocalDateTime createdAt,
//                                       LocalDateTime modifiedAt,
//                                       int voteCount, Boolean voteResult,
//                                       int voteAgree, int voteDisagree,
//                                       Boolean revoteResult,
//                                       int revoteAgree,
//                                       int revoteDisagree,
//                                       Vote.VoteStatus voteStatus,
//                                       Boolean isPublic,
//                                       Boolean isEditable,
//                                       Boolean isActive,
//                                       Boolean isVotedMember,
//                                       Boolean isBookmarkedMember) {
//        SeriesResponseDto seriesResponseDto = new SeriesResponseDto();
//
//        seriesResponseDto.setId(id);
//        seriesResponseDto.setTitle(title);
//        seriesResponseDto.setImage(image);
//        seriesResponseDto.setDaylogCount(daylogCount);
//        seriesResponseDto.setCreatedAt(createdAt);
//        seriesResponseDto.setModifiedAt(modifiedAt);
//        seriesResponseDto.setVoteCount(voteCount);
//        seriesResponseDto.setVoteResult(voteResult);
//        seriesResponseDto.setVoteAgree(voteAgree);
//        seriesResponseDto.setVoteDisagree(voteDisagree);
//        seriesResponseDto.setVoteResult(revoteResult);
//        seriesResponseDto.setRevoteAgree(revoteAgree);
//        seriesResponseDto.setRevoteDisagree(revoteDisagree);
//        seriesResponseDto.setSeriesStatus(voteStatus);
//        seriesResponseDto.setIsPublic(isPublic);
//        seriesResponseDto.setIsEditable(isEditable);
//        seriesResponseDto.setIsActive(isActive);
//        seriesResponseDto.setIsVotedMember(isVotedMember);
//        seriesResponseDto.setIsBookmarkedMember(isBookmarkedMember);
//
//        return seriesResponseDto;
//    }


}
