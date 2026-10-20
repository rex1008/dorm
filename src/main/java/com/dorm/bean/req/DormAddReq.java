package com.dorm.bean.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DormAddReq {

    @NotBlank
    private String dormNo;
    @NotNull
    private Integer capacity;
    @NotNull
    private Long dormBuildingId;

    /**
     * 分配的学生id列表
     */
    private List<Long> studentIds;
}
