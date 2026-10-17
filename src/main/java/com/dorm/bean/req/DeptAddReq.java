package com.dorm.bean.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeptAddReq {

    @NotBlank
    private String deptName;
}
