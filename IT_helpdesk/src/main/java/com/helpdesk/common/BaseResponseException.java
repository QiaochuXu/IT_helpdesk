/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 *
 * @date 2020-07-08
 * @author dianshi-rd (dianshi-rd@baidu.com)
 */
package com.helpdesk.common;


/**
 * @author xuqiaochu
 */
public class BaseResponseException extends RuntimeException {
    private int code;

    private String message;

    private ResponseErrorCode responseErrorCode;

    public BaseResponseException(ResponseErrorCode code) {
        super();
        this.code = 1;
        this.message = code.getMessage();
        this.responseErrorCode = code;
    }

    public BaseResponseException(ResponseErrorCode code, String message) {
        super();
        this.code = 1;
        this.message = message;
        code.setMessage(message);
        this.responseErrorCode = code;
    }

    public ResponseErrorCode getResponseErrorCode() {
        return responseErrorCode;
    }

    public void setResponseErrorCode(ResponseErrorCode code) {
        this.responseErrorCode = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

