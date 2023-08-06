package com.ssts.ssts.global.auth.utils;

import lombok.Getter;

//FIXME enum수정하기 switch문 안쓰기
public enum SocialType {
    KAKAO("kakao"),
    NAVER("naver"),
    GOOGLE("google"),
    TEST_SSTS("test_ssts");

    @Getter
    String str;

    SocialType(String testSsts) {
    }

    public static SocialType stringToSocialType(String str) {
        for(SocialType type:SocialType.values()){
            if(str.equals(type.getStr())) return type;
        }
        return TEST_SSTS;
    }
}
