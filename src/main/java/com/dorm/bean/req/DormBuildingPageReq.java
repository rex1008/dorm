package com.dorm.bean.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DormBuildingPageReq extends BasePageReq {

    private String dormBuildingNo;
    private String dormBuildingName;
    /**
     * M:男生宿舍 F:女生宿舍
     */
    private String type;
}
