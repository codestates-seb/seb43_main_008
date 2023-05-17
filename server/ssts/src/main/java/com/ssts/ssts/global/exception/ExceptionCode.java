package com.ssts.ssts.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    //TODO 사용자한테 노출되는 예외랑 개발자한테만 보이는 예외를 나눠야할까?

    MEMBER_NOT_FOUND(2000, "사용자 정보가 없습니다"),
    MEMBER_EXISTS(2001, "Member exists"),
    EMAIL_DUPLICATE(2002, "이메일이 중복되었습니다"),
    PHONENUMBER_DUPLICATE(2002, "휴대폰 번호가 중복되었습니다"),
    NOT_IMPLEMENTATION(2003, "Not Implementation"),
    INVALID_MEMBER_STATUS(2004, "Invalid member status"),
    SERIES_NOT_EXISTS(2005, "시리즈를 찾을 수 없습니다"),
    SECURITY_GUEST_OBJECT_SERIALIZE_ERROR(2006, "GUEST object information json serialization error"),
    SECURITY_NO_CREDENTIALS(2007,"사용자 정보가 없습니다"),
    NICKNAME_DUPLICATE(2008, "닉네임이 중복되었어요."),
    SECURITY_TEST_LOGIN_NO_MEMBER(2009, "Test용 로그인 에러: 사용자 없어요"),
    NOT_ALLOWED_PERMISSION(2010, "허용되지 않은 사용자 입니다."),

    FOLLOW_NOT_AVAILABLE(1050, "팔로우 할 수 없는 상대예요."),
    IS_ALREADY_FOLLOWING(1051,"이미 팔로우한 상대예요."),
    IS_ALREADY_UNFOLLOWING(1051,"팔로우한 상대가 아니예요."),

    MEMBER_ALREADY_VOTE(1001, "더이상의 투표는 불가능해요."),
    VOTE_NOT_FOUND(1002, "존재하지 않는 투표예요."),
    CAN_NOT_MAKE_VOTE(1003, "더이상 투표를 개설할 수 없어요."),
    DEADLINE_FALL_SHORT(1004, "이미 투표가 진행중이예요."),
    NOT_HAVE_VOTE_AUTHORITY(1005, "투표를 종료할 수 없는 사용자예요."),
    VOTE_RESULT_IS_NOT_EXSIST(1006, "투표 결과가 존재하지 않아요."),
    THIS_VOTE_RESULT_IS_TRUE(1007, "이 투표는 이미 졸업했어요!"),
    CAN_NOT_VOTE_VALUE(1008, "투표할 수 없는 값이예요."),
    BOOKMARK_IS_DUPLICATION(1009, "이미 북마크한 시리즈예요."),
    BADGE_NOT_FOUND(1010, "존재하지 않는 뱃지예요."),
    ALREADY_HAVE_BADGE(1011, "이미 취득한 뱃지예요.");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
