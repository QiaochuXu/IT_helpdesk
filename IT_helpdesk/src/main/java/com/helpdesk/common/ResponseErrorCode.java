package com.helpdesk.common;

import org.springframework.http.HttpStatus;

/**
 * 自定义错误码
 * Customised error codes
 */
public enum ResponseErrorCode {
    // user
    USER_DETAIL_EXCEPTION(HttpStatus.BAD_REQUEST, "ParamsError", "user id cannot be null"),
    USER_USER_NAME_EXCEPTION(HttpStatus.BAD_REQUEST, "ParamsError", "user name is not exist"),
    USER_NAME_CODE_EXCEPTION(HttpStatus.BAD_REQUEST, "ParamsError", "username has already exist"),
    USER_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "ParamsError", "wrong password or username"),
    // tag
    TAG_NAME_EXCEPTION(HttpStatus.BAD_REQUEST, "ParamsError", "tag name exist");



    private HttpStatus status;
    private String code;
    private String message;

    ResponseErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
