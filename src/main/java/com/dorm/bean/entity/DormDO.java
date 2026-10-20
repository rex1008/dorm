package com.dorm.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("dorm")
@Data
public class DormDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String dormNo;
    private Integer capacity;
    private Long dormBuildingId;
}
