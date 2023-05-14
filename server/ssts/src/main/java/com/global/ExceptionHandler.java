package com.global;

import com.ssts.ssts.exception.BusinessLogicException;
import com.ssts.ssts.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<String> handleException(BusinessLogicException ex){
        ResponseEntity<String> response=ResponseEntity.badRequest().body("Occurred BusinessLoginException.");

        if (ex.getExceptionCode()== ExceptionCode.EMAIL_DUPLICATE) {
            response=ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate email.");
        } else if (ex.getExceptionCode() == ExceptionCode.MEMBER_EXISTS) {
            response=ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Not found.");
        }

        return response;
    }
}
