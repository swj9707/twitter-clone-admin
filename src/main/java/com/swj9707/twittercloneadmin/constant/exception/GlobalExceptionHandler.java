package com.swj9707.twittercloneadmin.constant.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Controller
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponse> handler(CustomException e) {
        CustomExceptionResponse response = new CustomExceptionResponse(e.getStatusCode(), e.getErrorMessage());
        return new ResponseEntity<>(response, e.getStatusCode());
    }
}
