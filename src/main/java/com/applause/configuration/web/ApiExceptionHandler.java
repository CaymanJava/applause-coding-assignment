package com.applause.configuration.web;

import com.applause.dto.Problem;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Problem> handleException(Exception exception) {
        return new ResponseEntity<>(new Problem(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
