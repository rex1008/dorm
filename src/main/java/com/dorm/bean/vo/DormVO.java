package com.dorm.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class DormVO {

    private Long id;
    private String dormNo;
    private Integer capacity;
    private Long dormBuildingId;
    private String dormBuildingName;

    private List<StudentVO> studentList;
}
