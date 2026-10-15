package com.dorm.bean.req;

import com.dorm.enums.SexEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ManagerUpdateReq {

    @NotNull
    private Long id;

    @NotBlank
    private String realName;

    /**
     * @see SexEnum
     */
    @NotBlank
    private String sex;

    private String avatarPath;
}
