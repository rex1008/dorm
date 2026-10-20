package com.dorm.bean.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class DormBuildingUpdateReq {

    @NotNull
    private Long id;

    @NotBlank
    private String dormBuildingNo;
    @NotBlank
    private String dormBuildingName;

    /**
     * M:男生宿舍 F:女生宿舍
     */
    @NotBlank
    private String type;
    @NotNull
    private Date completeTime;
}
