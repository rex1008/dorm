package com.dorm.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dorm.bean.entity.DormBuildingDO;
import com.dorm.bean.entity.DormDO;
import com.dorm.bean.entity.StudentDO;
import com.dorm.bean.req.StudentAddReq;
import com.dorm.bean.req.StudentPageReq;
import com.dorm.bean.req.StudentUpdateReq;
import com.dorm.bean.vo.PageVO;
import com.dorm.bean.vo.StudentVO;
import com.dorm.enums.SexEnum;
import com.dorm.ex.BizEx;
import com.dorm.mapper.NativeSqlMapper;
import com.dorm.mapper.StudentMapper;
import com.dorm.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService extends ServiceImpl<StudentMapper, StudentDO> {

    @Resource
    private NativeSqlMapper nativeSqlMapper;
    @Resource
    private DormBuildingService dormBuildingService;

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

    @Transactional(rollbackFor = Exception.class)
    public void assignDorm(DormDO deptDO, List<Long> studentIds) {
        if (CollectionUtil.isEmpty(studentIds)) {
            return;
        }
        // 判断宿舍容量是否超出
        if (studentIds.size() > deptDO.getCapacity()) {
            throw new BizEx("宿舍容量不足");
        }
        // 校验性别
        DormBuildingDO dormBuilding = dormBuildingService.getById(deptDO.getDormBuildingId());
        if (dormBuilding == null) {
            throw new BizEx("宿舍所在宿舍楼不存在");
        }
        List<StudentDO> selectedStudents = listByIds(studentIds);
        if (CollectionUtil.isEmpty(selectedStudents)) {
            throw new BizEx("选择的学生不存在");
        }
        long count = selectedStudents.stream()
                .filter(student -> !Objects.equals(student.getSex(), dormBuilding.getType()))
                .count();
        if (count > 0) {
            String msg = String.format("当前宿舍只能分配性别为：%s的学生", SexEnum.getDescByName(dormBuilding.getType()));
            throw new BizEx(msg);
        }

        List<StudentDO> toUpdateList = studentIds.stream().map(studentId -> {
            StudentDO studentDO = new StudentDO();
            studentDO.setId(studentId);
            studentDO.setDormId(deptDO.getId());
            return studentDO;
        }).collect(Collectors.toList());
        updateBatchById(toUpdateList);
    }

}