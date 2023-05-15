package com.ssts.ssts.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_FOUND(2000, "Member not found"),
    MEMBER_EXISTS(2001, "Member exists"),
    EMAIL_DUPLICATE(2002, "Email duplicate"),
    NOT_IMPLEMENTATION(2003, "Not Implementation"),
    INVALID_MEMBER_STATUS(2004, "Invalid member status"),
    SERIES_NOT_EXISTS(2005, "Series Not Exists");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
