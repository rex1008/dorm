package com.dorm.bean.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DormBuildingVO {

    private Long id;

    private String dormBuildingNo;
    private String dormBuildingName;

    /**
     * M:男生宿舍 F:女生宿舍
     */
    private String type;
    private Date completeTime;
}
