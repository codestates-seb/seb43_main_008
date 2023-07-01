package com.ssts.ssts.domain.vote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssts.ssts.domain.common.BaseTimeEntity;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.series.entity.Series;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Vote extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //투표 개설 시간
    @Column
    private LocalDateTime voteCreatedAt;

    //투표 마감 시간(임시)
    @Column
    private LocalDateTime voteEndAt;

    //radis, 테스트

    //시간 인터페이스 끌어오는 거 보고 하기 modifiedAt, deletedAt

    @Column(name = "vote_result")
    private Boolean voteResult;

    @Column
    private int agree;

    @Column
    private int disagree;

    @ColumnDefault("0")
    @Column
    private int voteCount; //투표함 개수

    @ColumnDefault("0")
    @Column
    private int votePapers; //투표지 개수

    @Column
    private Boolean result;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    @Enumerated(value = EnumType.STRING)
    @Column
    private VoteStatus status = VoteStatus.SERIES_ACTIVE;

    public enum VoteStatus {
        SERIES_ACTIVE("투표 미완료"),
        SERIES_SLEEP("투표 중"),
        SERIES_QUIT("투표 완료");

        @Getter
        private String status;

        VoteStatus(String status) {
            this.status = status;
        }
    }


    public static Vote of(Vote.VoteStatus status, LocalDateTime createAt) {
        Vote vote = new Vote();

        vote.setStatus(status);
        vote.setVoteCreatedAt(createAt);

        //일단 만든뒤에 할당하려고
        //vote.setVoteCount(voteCount);
        //vote.setVoteEndAt(voteEndAt);

        return vote;
    }

}

