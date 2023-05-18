package com.ssts.ssts.domain.badges.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BadgeResponse {

    Long badgeId;
    Boolean isAcquired;
    String img;

    public static BadgeResponse of(Long badgeId, Boolean isAcquired, String img){
        BadgeResponse badge = new BadgeResponse();
        badge.badgeId = badgeId;
        badge.isAcquired = isAcquired;
        badge.img = img;
        return badge;
    }



    //LocalDateTime createAt;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class isAcquiredResponse extends BadgeResponse{

        String name;
        String mainText;
        String subText;

        //획득 뱃지 response
        public static isAcquiredResponse of(Long badgeId, String name, String setmainText, String setsubText, String img, Boolean isAcquired){
        BadgeResponse.isAcquiredResponse badge = new isAcquiredResponse();
        badge.setBadgeId(badgeId);
        badge.setName(name);
        badge.setMainText(setmainText);
        badge.setSubText(setsubText);
        badge.setImg(img);
        badge.setIsAcquired(isAcquired);
        return badge;
        }
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class unAcquiredResponse extends BadgeResponse{

        String name;
        String mainText;
        String subText;

        public static unAcquiredResponse of(Long badgeId, String name, String mainText, String subText, String img, Boolean isAcquired){
            BadgeResponse.unAcquiredResponse badge = new unAcquiredResponse();
            badge.setBadgeId(badgeId);
            badge.setName(name);
            badge.setMainText(mainText);
            badge.setSubText(subText);
            badge.setImg(img);
            badge.setIsAcquired(isAcquired);
            return badge;
        }
    }

    @JsonIgnore //응답값에서 Id를 제거
    public Long getId() { //스트림 쓰려고 추가
        return badgeId;
    }
}
