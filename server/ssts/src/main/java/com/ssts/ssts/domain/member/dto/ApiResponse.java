/*
package com.ssts.ssts.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ApiResponse {
    @JsonIgnore

    private final KeeweRtnConsts status;
    private final String message;
    private final int code;
    // Consts -> 사용자에게 어떤 상태값, 안내메세지, code

    private final T data;  // 실제 응답값

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<> (
                KeeweRtnConsts.NRM000,
                KeeweRtnConsts.NRM000.getDescription(),
                KeeweRtnConsts.NRM000.getCode(),
                null
        );
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<> (
                KeeweRtnConsts.NRM000,
                KeeweRtnConsts.NRM000.getDescription(),
                KeeweRtnConsts.NRM000.getCode(),
                data
        );
    }

    public static <T> ApiResponse<T> failure(KeeweRtnConsts status) {
        return new ApiResponse<>(
                status,
                status.getDescription(),
                status.getCode(),
                null
        );
    }

    public static <T> ApiResponse<T> failure(KeeweRtnConsts status, String message) {
        return new ApiResponse<>(
                status,
                message,
                status.getCode(),
                null
        );
    }
}
*/
