package com.dorm.bean.vo;

import lombok.Data;

@Data
public class StudentVO {

    private Long id;
    private String studentNo;
    private String studentName;
    /**
     * @see com.dorm.enums.SexEnum
     */
    private String sex;
    private String photoPath;
    private Long clazzId;
    private String clazzName;
    private Long deptId;
    private String deptName;
    private Long dormId;
    private String dormNo;
    private String dormBuildingName;
}
