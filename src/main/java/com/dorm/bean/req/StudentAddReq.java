package com.dorm.bean.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentAddReq {

    @NotBlank
    private String studentNo;
    @NotBlank
    private String studentName;
    /**
     * @see com.dorm.enums.SexEnum
     */
    @NotBlank
    private String sex;
    private String photoPath;
    @NotNull
    private Long clazzId;
}
