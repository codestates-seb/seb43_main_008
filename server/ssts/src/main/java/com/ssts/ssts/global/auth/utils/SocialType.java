package com.ssts.ssts.global.auth.utils;

public enum SocialType {
    KAKAO, NAVER, GOOGLE, TEST_SSTS;

    public static SocialType stringToSocialType(String strType){
        switch (strType){
            case "google":
                return GOOGLE;
            case "kakao":
                return KAKAO;
            case "naver":
                return NAVER;
            default:
                return TEST_SSTS;
        }
    }
}
