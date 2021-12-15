package com.helpdesk.common;

import org.springframework.http.HttpStatus;

/**
 * 自定义错误码
 */
public enum ResponseErrorCode {
    // user
    USER_DETAIL_EXCEPTION(HttpStatus.BAD_REQUEST, "ParamsError", "用户id不能为空"),
    USER_USER_NAME_EXCEPTION(HttpStatus.BAD_REQUEST, "ParamsError", "用户名不存在"),
    USER_NAME_CODE_EXCEPTION(HttpStatus.BAD_REQUEST, "ParamsError", "用户名已存在"),
    USER_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "ParamsError", "用户密码错误"),
    // tag
    TAG_NAME_EXCEPTION(HttpStatus.BAD_REQUEST, "ParamsError", "标签名称存在");



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
