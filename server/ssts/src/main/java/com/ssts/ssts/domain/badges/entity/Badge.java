package com.ssts.ssts.domain.badges.entity;

import com.ssts.ssts.domain.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "badges")
public class Badge extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    //획득 mainText
    @Column(name = "earn_maintext")
    private String earnMainText;

    @Column(name="earn_subtext")
    private String earnSubText;

    //미획득 mainText
    @Column(name = "unearn_maintext")
    private String unEarnMainText;

    @Column(name = "unearn_subtext")
    private String unEarnSubText;

    @Column(name = "img")
    private String img; //이미지

    @Column(name = "is_acquired")
    private Boolean isAcquired=false; //획득여부

    //획득of post 테스트시에 쓸려고 임시로 만듦
    public static Badge of(String name, String earnMainText, String earnSubText, String unEarnMainText, String unEarnSubText, Boolean isAcquired, String img){
        Badge badge = new Badge();
        badge.name = name;
        badge.earnMainText = earnMainText;
        badge.earnSubText = earnSubText;
        badge.unEarnMainText = unEarnMainText;
        badge.unEarnSubText = unEarnSubText;
        badge.isAcquired = isAcquired;
        badge.img = img;
        return badge;
    }

    //미획득
//    public static Badge of(String name, String ungetMainText, String ungetSubText, String img, Boolean isAcquired){
//        Badge badge = new Badge();
//        badge.name = name;
//        badge.ungetMainText = ungetMainText;
//        badge.ungetSubText = ungetSubText;
//        badge.img = img;
//        badge.isAcquired = isAcquired;
//
//        return badge;
//    }

}
