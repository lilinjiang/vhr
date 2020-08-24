package com.jiang.vhr.util;

/**
 * @author lilinjiang
 * 响应bean
 */
public class RespUtil {
    private Integer status;
    private String msg;
    private Object obj;

    public static RespUtil build() {
        return new RespUtil();
    }

    public static RespUtil ok(String msg) {
        return new RespUtil(200, msg, null);
    }

    public static RespUtil ok(String msg, Object obj) {
        return new RespUtil(200, msg, obj);
    }

    public static RespUtil error(String msg) {
        return new RespUtil(500, msg, null);
    }

    public static RespUtil error(String msg, Object obj) {
        return new RespUtil(500, msg, obj);
    }

    private RespUtil() {
    }

    private RespUtil(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public RespUtil setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespUtil setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public RespUtil setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}
