package com.dorm.bean.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentPageReq extends BasePageReq {

    private String studentNo;
    private String studentName;
    /**
     * @see com.dorm.enums.SexEnum
     */
    private String sex;
    private Long deptId;
    private Long clazzId;
}
