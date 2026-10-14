package com.dorm.res;

import com.dorm.enums.CodeEnum;
import lombok.Data;

@Data
public class Result<T> {

    private Integer code = CodeEnum.SUCCESS.getCode();
    private String msg = CodeEnum.SUCCESS.getMsg();
    private T data;
    private Long total;
    private Boolean success = Boolean.TRUE;

    private Result() {

    }

    public static <T> Result<T> buildSuccess() {
        return new Result<>();
    }

    public static <T> Result<T> buildSuccess(T t) {
        Result<T> result = buildSuccess();
        result.setData(t);
        return result;
    }

    public static <T> Result<T> buildSuccess(T t, Long total) {
        Result<T> result = buildSuccess(t);
        result.setTotal(total);
        return result;
    }

    public static <T> Result<T> buildFailure(String msg) {
        Result<T> result = new Result<>();
        result.setCode(null);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> buildFailure(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> buildFailure(CodeEnum codeEnum) {
        Result<T> result = new Result<>();
        result.setCode(codeEnum.getCode());
        result.setMsg(codeEnum.getMsg());
        return result;
    }
}
