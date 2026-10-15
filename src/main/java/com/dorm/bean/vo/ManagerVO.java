package com.dorm.bean.vo;

import com.dorm.enums.SexEnum;
import lombok.Data;

@Data
public class ManagerVO {

    private Long id;

    private String username;

    private String realName;

    /**
     * @see SexEnum
     */
    private String sex;

    private String avatarPath;
}
