package com.ssts.ssts.domain.member.constant;

import lombok.Getter;

public enum MemberStatus {
    ACTIVE("활동",0),
    DORMANCY("휴면",1),
    WITHDRAW("탈퇴",2);


    @Getter
    private String strStatus;
    @Getter
    private int numStatus;

    MemberStatus(String strStatus, int numStatus){
        this.strStatus = strStatus;
        this.numStatus = numStatus;
    }



}
