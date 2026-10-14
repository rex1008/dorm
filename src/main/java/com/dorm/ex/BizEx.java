package com.dorm.ex;

import com.dorm.enums.CodeEnum;

public class BizEx extends RuntimeException {

    public Integer getCode() {
        return code;
    }

    private Integer code;

    public BizEx(String message) {
        super(message);
    }

    public BizEx(CodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }
}
