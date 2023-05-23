package com.ssts.ssts.global.auth.utils;

public final class TestConstants {

    //http://ec2-3-37-46-164.ap-northeast-2.compute.amazonaws.com:8080

    public static final String REDIRECT_URL="http://localhost:8080/login/oauth2/code/";

    public static final String BE_BASE_URL="http://localhost:8080";

    public static final String FE_BASE_URL="http://localhost:3000";

    public static final String TOKEN_CHECK_URI="/token/check";

    public static final String TOKEN_REISSUE_URI="/token/reissue";
}
