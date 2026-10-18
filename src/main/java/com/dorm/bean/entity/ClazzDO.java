package com.dorm.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("clazz")
@Data
public class ClazzDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String clazzNo;
    private String clazzName;
    private Long deptId;
}
