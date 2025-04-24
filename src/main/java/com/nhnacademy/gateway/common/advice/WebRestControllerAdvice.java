package com.nhnacademy.gateway.common.advice;

import com.nhnacademy.gateway.exception.LoginProcessException;
import com.nhnacademy.gateway.exception.RegisterProcessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler(RegisterProcessException.class)
    public ResponseEntity<String> registerProcessException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(LoginProcessException.class)
    public ResponseEntity<String> loginProcessException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
