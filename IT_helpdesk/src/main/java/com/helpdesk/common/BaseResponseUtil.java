package com.helpdesk.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuqiaochu
 */
@Data
public class BaseResponseUtil<T>  extends Object{

    private T result;

    private Map<String, Object> jo;

    /**
     * 请求成功
	 * Succeed!
     */
    public static final int SUCCESS = 20000;

    /**
     * 请求异常
	 * Failed!
     */
    public static final int FAILED = -1;


    public static Map<String, Object> constructResponse(int code, String msg) {
        Map<String, Object> jo = new HashMap<>(3);
        jo.put("code", code);
        jo.put("msg", msg);
        jo.put("data", null);
        return jo;
    }

    public static Map<String, Object> constructResponse(int code, String msg, Object data) {
        Map<String, Object> jo = new HashMap<>(3);
        jo.put("code", code);
        jo.put("msg", msg);
        jo.put("data", data);
        return jo;
    }

    public  BaseResponseUtil<T> constructResponseValid(int code, String msg, Object data) {
        Map<String, Object> jo = new HashMap<>(3);
        jo.put("code", code);
        jo.put("msg", msg);
        jo.put("data", data);
        this.jo = jo;
        return this;
    }

    public static Map<String, Object> constructResponseValid(int code, Object data) {
        Map<String, Object> jo = new HashMap<>(2);
        jo.put("code", code);
        jo.put("data", data);
        return jo;
    }
}
