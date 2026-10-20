package com.dorm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dorm.bean.entity.StudentDO;
import com.dorm.bean.req.DormPageReq;
import com.dorm.bean.req.StudentPageReq;
import com.dorm.bean.vo.DormVO;
import com.dorm.bean.vo.StudentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NativeSqlMapper {

    IPage<StudentVO> queryStudentPage(IPage<StudentDO> iPage, @Param("pageReq") StudentPageReq pageReq);

    StudentVO queryStudentById(@Param("id") Long id);

    Long queryDormCount(DormPageReq pageReq);

    List<DormVO> queryDormList(DormPageReq pageReq);
}
