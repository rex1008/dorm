package com.dorm.bean.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeptPageReq extends BasePageReq {

    @NotBlank
    private String deptName;
}
