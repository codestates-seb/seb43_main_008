package com.ssts.ssts.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssts.ssts.global.exception.ExceptionCode;
import com.ssts.ssts.global.exception.RtnHttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ApiResponse<T> {

    private final String message;
    private final int code;
    private final T data;  // 실제 응답값

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<> (
                "성공",
                200,
                null
        );
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<> (
                "성공",
                200,
                data
        );
    }

    public static <T> ApiResponse<T> failure(ExceptionCode ec) {
        return new ApiResponse<>(
                ec.getMessage(),
                ec.getRtnHttpStatus().getStatusCode(),
                null
        );
    }

    /*public static <T> ApiResponse<T> failure(ExceptionCode ec, String message) {
        return new ApiResponse<>(
                message,
                ec.getCode(),
                null
        );
    }*/
}
