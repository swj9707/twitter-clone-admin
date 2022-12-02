package com.swj9707.twittercloneadmin.constant.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomExceptionResponse {
    private HttpStatus errorCode;
    private String errorMessage;
}
