package com.dorm.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dorm.enums.SexEnum;
import lombok.Data;

@TableName("manager")
@Data
public class ManagerDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String realName;

    private String pwd;

    /**
     * @see SexEnum
     */
    private String sex;

    private String avatarPath;
}
