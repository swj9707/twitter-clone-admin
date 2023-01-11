package com.swj9707.twittercloneadmin.constant.exception;

import com.swj9707.twittercloneadmin.constant.enums.ErrorCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
@Getter
@ToString
public class CustomException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String errorName;
    private final String errorMessage;

    public CustomException(ErrorCode errorCode) {
        this.statusCode = errorCode.getCode();
        this.errorName = errorCode.getErrorName();
        this.errorMessage = errorCode.getErrorMessage();
    }
}
