package com.ssts.ssts.global;

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
        ResponseEntity<String> response=ResponseEntity.badRequest().body("Occurred BusinessLoginException.");

        if (ex.getExceptionCode()== ExceptionCode.EMAIL_DUPLICATE) {
            response=ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionCode.EMAIL_DUPLICATE.getMessage());

        } else if (ex.getExceptionCode() == ExceptionCode.MEMBER_NOT_FOUND) {
            response=ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionCode.MEMBER_NOT_FOUND.getMessage());

        } else if(ex.getExceptionCode()==ExceptionCode.SECURITY_GUEST_OBJECT_SERIALIZE_ERROR){
            response=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionCode.SECURITY_GUEST_OBJECT_SERIALIZE_ERROR.getMessage());

        } else if(ex.getExceptionCode()==ExceptionCode.SECURITY_NO_CREDENTIALS){
            response=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionCode.SECURITY_NO_CREDENTIALS.getMessage());

        } else if (ex.getExceptionCode() == ExceptionCode.NICKNAME_DUPLICATE) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionCode.NICKNAME_DUPLICATE.getMessage());

        } else if (ex.getExceptionCode() == ExceptionCode.PHONENUMBER_DUPLICATE) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionCode.PHONENUMBER_DUPLICATE.getMessage());

        } else if (ex.getExceptionCode() == ExceptionCode.SECURITY_TEST_LOGIN_NO_MEMBER) {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionCode.PHONENUMBER_DUPLICATE.getMessage());
        }

        return response;
    }
}
