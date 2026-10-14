package com.dorm.enums;

public enum CodeEnum {

    SUCCESS(200, "成功"),
    PRAM_ERR(10000, "参数异常"),


    BIZ_ERR(30000, "业务异常"),
    AUTH_ERR(30001, "权限不足"),


    SYS_ERR(90000, "系统异常"),

    ;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final Integer code;
    private final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
