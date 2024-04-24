package com.green.onezo.global;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String httpMessageNotReadableException(HttpMessageNotReadableException e){
        return "DTO 작성해서 요청하세요.";
    }
}
