package com.ssts.ssts.global.exception.handler;

import com.ssts.ssts.global.utils.MultipleResponseDto.ApiResponse;
import com.ssts.ssts.global.exception.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessLogicException.class)
    public ApiResponse<String> handleException(BusinessLogicException ex){

        return ApiResponse.failure(ex.getExceptionCode());
    }
}
