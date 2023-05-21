package com.ssts.ssts.domain.badges.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BadgePostDto {
    private String name;
    private String earnMainText;
    private String earnSubText;
    private String unEarnMainText;
    private String unEarnSubText;
    private Boolean isAcquired;
    private String img;


}
