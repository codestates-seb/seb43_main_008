package com.ssts.ssts.domain.badges;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BadgeResponse {

    Long badgeId;
    String name;
    String img;
    //LocalDateTime createAt;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class isAcquiredResponse extends BadgeResponse{

        Boolean isAcquired;
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
        Boolean isAcquired;
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
}
