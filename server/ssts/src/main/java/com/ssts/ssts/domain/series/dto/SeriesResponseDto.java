package com.ssts.ssts.domain.series.dto;


import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.series.entity.Series;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
public class SeriesResponseDto {

    private Long id;

    private String title;

    private String image;

    private int daylogCount;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private int voteCount;

    private Boolean voteResult;

    private int voteAgree;

    private int voteDisagree;

    private Boolean revoteResult;

    private int revoteAgree;

    private int revoteDisagree;

    private Series.VoteStatus seriesStatus;

    private Boolean isPublic;

    private Boolean isEditable;

    private Boolean isActive;

    private Boolean isVotedMember;

    private int totalVote;

    private Boolean isBookmarkedMember;




    public static SeriesResponseDto of(Long id,
                                       String title,
                                       String image,
                                       int daylogCount,
                                       LocalDateTime createdAt,
                                       LocalDateTime modifiedAt,
                                       int voteCount, Boolean voteResult,
                                       int voteAgree, int voteDisagree,
                                       Boolean revoteResult,
                                       int revoteAgree,
                                       int revoteDisagree,
                                       Series.VoteStatus voteStatus,
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
        seriesResponseDto.setVoteResult(voteResult);
        seriesResponseDto.setVoteAgree(voteAgree);
        seriesResponseDto.setVoteDisagree(voteDisagree);
        seriesResponseDto.setVoteResult(revoteResult);
        seriesResponseDto.setRevoteAgree(revoteAgree);
        seriesResponseDto.setRevoteDisagree(revoteDisagree);
        seriesResponseDto.setSeriesStatus(voteStatus);
        seriesResponseDto.setIsPublic(isPublic);
        seriesResponseDto.setIsEditable(isEditable);
        seriesResponseDto.setIsActive(isActive);
        seriesResponseDto.setTotalVote(totalVote);


        return seriesResponseDto;
    }

    public static SeriesResponseDto of(Long id,
                                       String title,
                                       String image,
                                       int daylogCount,
                                       LocalDateTime createdAt,
                                       LocalDateTime modifiedAt,
                                       int voteCount, Boolean voteResult,
                                       int voteAgree, int voteDisagree,
                                       Boolean revoteResult,
                                       int revoteAgree,
                                       int revoteDisagree,
                                       Series.VoteStatus voteStatus,
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
        seriesResponseDto.setVoteResult(voteResult);
        seriesResponseDto.setVoteAgree(voteAgree);
        seriesResponseDto.setVoteDisagree(voteDisagree);
        seriesResponseDto.setVoteResult(revoteResult);
        seriesResponseDto.setRevoteAgree(revoteAgree);
        seriesResponseDto.setRevoteDisagree(revoteDisagree);
        seriesResponseDto.setSeriesStatus(voteStatus);
        seriesResponseDto.setIsPublic(isPublic);
        seriesResponseDto.setIsEditable(isEditable);
        seriesResponseDto.setIsActive(isActive);
        seriesResponseDto.setIsVotedMember(isVotedMember);
        seriesResponseDto.setIsBookmarkedMember(isBookmarkedMember);

        return seriesResponseDto;
    }


}
