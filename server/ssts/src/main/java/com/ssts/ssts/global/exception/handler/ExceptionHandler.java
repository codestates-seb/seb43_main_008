package com.ssts.ssts.global.exception.handler;

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
    public ResponseEntity<String> handleException(BusinessLogicException ex){

        ResponseEntity<String> response=ResponseEntity
                .status(ex.getExceptionCode().getRtnHttpStatus().getStatusCode())
                .body(ex.getExceptionCode().getMessage());

        return response;
    }
}
