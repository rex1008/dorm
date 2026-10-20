package com.dorm.bean.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DormPageReq extends BasePageReq {

    private String dormNo;
    private Long dormBuildingId;
}
