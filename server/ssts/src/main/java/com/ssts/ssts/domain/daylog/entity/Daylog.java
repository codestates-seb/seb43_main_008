package com.ssts.ssts.domain.daylog.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssts.ssts.domain.common.BaseTimeEntity;
import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.series.entity.Series;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Entity
public class Daylog extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;
    @Nullable
    @Column
    private String image;

    @Column
    @ColumnDefault("0")
    private int daylogNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    public void setContent(String content) {
        this.content = content;
    }

    public void setContentImg(String img) {
        this.image = img;
    }

    public void setDaylogNumber(int daylogNumber) {
        this.daylogNumber = daylogNumber;
    }

    public void addSeries(Series series) {
        this.series = series;
        if (!this.series.getDaylogs().contains(this)) {
            this.series.getDaylogs().add(this);
        }
    }

    public void addMember(Member member){
        this.member = member;
        if(!this.member.getDaylogs().contains(this)){
            this.member.getDaylogs().add(this);
        }
    }




    public static Daylog of(String content, String contentImg){
        Daylog daylog = new Daylog();

        daylog.setContent(content);
        daylog.setContentImg(contentImg);

        return daylog;
    }

    public static Daylog of(String content){
        Daylog daylog = new Daylog();

        daylog.setContent(content);

        return daylog;
    }


}
