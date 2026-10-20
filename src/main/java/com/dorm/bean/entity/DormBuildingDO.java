package com.dorm.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("dorm_building")
@Data
public class DormBuildingDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String dormBuildingNo;
    private String dormBuildingName;
    private String type;
    private Date completeTime;
}
