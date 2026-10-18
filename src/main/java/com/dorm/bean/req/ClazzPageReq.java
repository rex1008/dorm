package com.dorm.bean.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClazzPageReq extends BasePageReq {

    private String clazzNo;
    private String clazzName;
    private Long deptId;
}
