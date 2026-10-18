package com.dorm.bean.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ClazzUpdateReq {

    @NotNull
    private Long id;

    @NotBlank
    private String clazzNo;
    @NotBlank
    private String clazzName;
    @NotNull
    private Long deptId;
}
