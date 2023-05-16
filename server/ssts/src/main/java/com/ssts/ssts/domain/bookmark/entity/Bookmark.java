package com.ssts.ssts.domain.bookmark.entity;

import com.ssts.ssts.domain.member.entity.Member;
import com.ssts.ssts.domain.series.entity.Series;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "bookmark")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="members_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="series_id")
    private Series series;

    public static Bookmark of(Member member, Series series){
        Bookmark bookmark = new Bookmark();
        bookmark.setMember(member);
        bookmark.setSeries(series);
        return bookmark;
    }
}
