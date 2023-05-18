package com.ssts.ssts.global.exception.handler;

import com.ssts.ssts.domain.member.dto.ApiResponse;
import com.ssts.ssts.global.exception.BusinessLogicException;
import com.ssts.ssts.global.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessLogicException.class)
    public ApiResponse<String> handleException(BusinessLogicException ex){

        return ApiResponse.failure(ex.getExceptionCode());
    }
}
