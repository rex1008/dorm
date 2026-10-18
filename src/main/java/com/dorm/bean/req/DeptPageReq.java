package com.dorm.bean.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeptPageReq extends BasePageReq {

    private String deptName;
}
