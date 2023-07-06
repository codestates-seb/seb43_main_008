package com.ssts.ssts.domain.series.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssts.ssts.domain.comment.Entity.Comment;
import com.ssts.ssts.domain.common.BaseTimeEntity;
import com.ssts.ssts.domain.daylog.entity.Daylog;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.vote.entity.Vote;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Series extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String image;

    @ColumnDefault("0")
    @Column
    private int daylogCount;

    @Column
    private Boolean isPublic = false;

    @Column
    private Boolean isEditable = true;

    @Column
    private Boolean isActive = true;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "series", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Daylog> daylogs = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "series", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "series", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();


    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIsPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public void setIsEditable(Boolean editable) {
        isEditable = editable;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public void setDaylogCount(int daylogCount) {
        this.daylogCount = daylogCount;
    }

    public static Series of(String title){
        Series series = new Series();

        series.setTitle(title);

        return series;

    }

    public static Series of(String title, Boolean isPublic){
        Series series = new Series();

        series.setTitle(title);
        series.setIsPublic(isPublic);

        return series;

    }

    public void addMember(Member member) {
        this.member = member;
        if (!this.member.getSeries().contains(this)) {
            this.member.getSeries().add(this);
        }
    }

    //FIXME 필요한지 확인하기
    //Vote 객체 추가 메서드
    public void addVote(Vote vote) {
        votes.add(vote);
        vote.setSeries(this);
    }

}
