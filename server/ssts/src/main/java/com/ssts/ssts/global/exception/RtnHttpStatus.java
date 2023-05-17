package com.ssts.ssts.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RtnHttpStatus {
    Success(200),
    Auth(400),
    Validation(410),
    System(500);

    @Getter
    private int statusCode;
}
