package com.ssts.ssts.exception;

import lombok.Getter;

public enum ExceptionCode {

    //TODO 사용자한테 노출되는 예외랑 개발자한테만 보이는 예외를 나눠야할까?

    MEMBER_NOT_FOUND(2000, "사용자 정보가 없습니다"),
    MEMBER_EXISTS(2001, "Member exists"),
    EMAIL_DUPLICATE(2002, "이메일이 중복되었습니다"),
    PHONENUMBER_DUPLICATE(2002, "휴대폰 번호가 중복되었습니다"),
    NOT_IMPLEMENTATION(2003, "Not Implementation"),
    INVALID_MEMBER_STATUS(2004, "Invalid member status"),
    SERIES_NOT_EXISTS(2005, "Series Not Exists"),
    SECURITY_GUEST_OBJECT_SERIALIZE_ERROR(2006, "GUEST object information json serialization error"),
    SECURITY_NO_CREDENTIALS(2007,"사용자 정보가 없습니다");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
