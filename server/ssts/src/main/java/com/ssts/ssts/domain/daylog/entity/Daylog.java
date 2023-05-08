package com.ssts.ssts.domain.daylog.entity;



import com.ssts.ssts.domain.series.entity.Series;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Daylog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;
    @Column
    private String img;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;


    public void setContent(String content) {
        this.content = content;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public void addSeries(Series series) {
        this.series = series;
        if (!this.series.getDaylogs().contains(this)) {
            this.series.getDaylogs().add(this);
        }
    }


}
