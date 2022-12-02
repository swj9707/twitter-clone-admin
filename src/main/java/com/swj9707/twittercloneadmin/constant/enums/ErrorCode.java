package com.swj9707.twittercloneadmin.constant.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request", "요청이 올바르지 않습니다."),
    PERMISSION_DENIED(HttpStatus.UNAUTHORIZED, "Unauthorized", "권한이 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden", "금지된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not found", "리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", "내부 서버 오류입니다. 관리자에게 문의하세요");

    HttpStatus errorCode;
    String errorName;
    String errorMessage;
    ErrorCode(HttpStatus errorCode, String errorName, String errorMessage) {
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.errorMessage = errorMessage;
    }
}
