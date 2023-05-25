package com.ssts.ssts.global.auth.utils;

public final class AuthConsts {

    //http://ec2-3-37-46-164.ap-northeast-2.compute.amazonaws.com:8080

    public static final String REDIRECT_URL="http://ec2-3-37-46-164.ap-northeast-2.compute.amazonaws.com:8080/login/oauth2/code/";

    public static final String BE_BASE_URL="http://localhost:8080";

    public static final String FE_BASE_URL="http://localhost:3000";

    public static final String TOKEN_CHECK_URI="/token/check";

    public static final String TOKEN_REISSUE_URI="/token/reissue";

    public static final String GOOGLE_URL="https://accounts.google.com";

    public static final String KAKAO_URL="https://kauth.kakao.com";

    public static final String NAVER_URL="https://nid.naver.com";
}
