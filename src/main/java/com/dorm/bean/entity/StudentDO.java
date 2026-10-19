package com.dorm.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("student")
@Data
public class StudentDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String studentNo;
    private String studentName;
    /**
     * @see com.dorm.enums.SexEnum
     */
    private String sex;
    private String photoPath;
    private Long clazzId;
    private Long dormId;
}
