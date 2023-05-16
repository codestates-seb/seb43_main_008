package com.ssts.ssts.domain.comment.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssts.ssts.domain.common.BaseTimeEntity;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.series.entity.Series;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addSeries(Series series) {
        this.series = series;
        if(!this.series.getComments().contains(this)){
            this.series.getComments().add(this);
        }
    }

    public void addMember(Member member) {
        this.member = member;
        if(!this.member.getComments().contains(this)){
            this.member.getComments().add(this);
        }
    }
}
