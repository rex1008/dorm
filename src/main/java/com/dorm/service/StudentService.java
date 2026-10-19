package com.dorm.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dorm.bean.entity.StudentDO;
import com.dorm.bean.req.StudentAddReq;
import com.dorm.bean.req.StudentPageReq;
import com.dorm.bean.req.StudentUpdateReq;
import com.dorm.bean.vo.PageVO;
import com.dorm.bean.vo.StudentVO;
import com.dorm.mapper.NativeSqlMapper;
import com.dorm.mapper.StudentMapper;
import com.dorm.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StudentService extends ServiceImpl<StudentMapper, StudentDO> {

    @Resource
    private DeptService deptService;
    @Resource
    private NativeSqlMapper nativeSqlMapper;

    @Transactional(rollbackFor = Exception.class)
    public void add(StudentAddReq addReq) {
        StudentDO entity = BeanUtil.copyProperties(addReq, StudentDO.class);
        save(entity);
    }

    public PageVO<StudentVO> queryPage(StudentPageReq pageReq) {
        IPage<StudentDO> iPage = PageUtil.buildPage(pageReq.getPageIndex(), pageReq.getPageSize());
        IPage<StudentVO> page = nativeSqlMapper.queryStudentPage(iPage, pageReq);
        return PageVO.of(page.getRecords(), page.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        removeById(id);
    }

    public StudentVO queryById(Long id) {
        return nativeSqlMapper.queryStudentById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyById(StudentUpdateReq updateReq) {
        // 兼容把照片更新为空的情况
        LambdaUpdateWrapper<StudentDO> luw = Wrappers.<StudentDO>lambdaUpdate()
                .set(StudentDO::getStudentNo, updateReq.getStudentNo())
                .set(StudentDO::getStudentName, updateReq.getStudentName())
                .set(StudentDO::getSex, updateReq.getSex())
                .set(StudentDO::getPhotoPath, updateReq.getPhotoPath())
                .set(StudentDO::getClazzId, updateReq.getClazzId())
                .eq(StudentDO::getId, updateReq.getId());
        update(new StudentDO(), luw);
    }

    public Boolean clazzUsed(Long clazzId) {
        LambdaQueryWrapper<StudentDO> lwq = Wrappers.<StudentDO>lambdaQuery()
                .eq(StudentDO::getClazzId, clazzId);
        return count(lwq) > 0;
    }
}