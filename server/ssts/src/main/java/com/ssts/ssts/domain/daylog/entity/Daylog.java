package com.ssts.ssts.domain.daylog.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssts.ssts.domain.common.BaseTimeEntity;
import com.ssts.ssts.domain.series.entity.Series;
import lombok.Getter;
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
    private String contentimg;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;


    public void setContent(String content) {
        this.content = content;
    }

    public void setContentImg(String img) {
        this.contentimg = img;
    }

    public void addSeries(Series series) {
        this.series = series;
        if (!this.series.getDaylogs().contains(this)) {
            this.series.getDaylogs().add(this);
        }
    }


    public static Daylog of(String content, String contentImg){
        Daylog daylog = new Daylog();

        daylog.setContent(content);
        daylog.setContentImg(contentImg);

        return daylog;
    }


}
