package com.dorm.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dorm.bean.entity.DormDO;
import com.dorm.bean.req.DormAddReq;
import com.dorm.bean.req.DormPageReq;
import com.dorm.bean.req.DormUpdateReq;
import com.dorm.bean.vo.DormVO;
import com.dorm.bean.vo.PageVO;
import com.dorm.mapper.DormMapper;
import com.dorm.mapper.NativeSqlMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class DormService extends ServiceImpl<DormMapper, DormDO> {

    @Resource
    private StudentService studentService;
    @Resource
    private NativeSqlMapper nativeSqlMapper;

    @Transactional(rollbackFor = Exception.class)
    public void add(DormAddReq addReq) {
        DormDO deptDO = BeanUtil.copyProperties(addReq, DormDO.class);
        save(deptDO);

        // 分配宿舍
        studentService.assignDorm(deptDO, addReq.getStudentIds());
    }

    public PageVO<DormVO> queryPage(DormPageReq pageReq) {
        Long total = nativeSqlMapper.queryDormCount(pageReq);
        if (total.equals(0L)) {
            return PageVO.of(Collections.emptyList(), 0L);
        }
        List<DormVO> list = nativeSqlMapper.queryDormList(pageReq);
        return PageVO.of(list, total);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
//        if (CollectionUtil.isNotEmpty(clazzService.listByDormId(id))) {
//            throw new BizEx("系别已被班级使用，无法删除");
//        }
        removeById(id);
    }

    public DormVO queryById(Long id) {
        DormDO record = getById(id);
        return BeanUtil.copyProperties(record, DormVO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyById(DormUpdateReq updateReq) {
        DormDO deptDO = BeanUtil.copyProperties(updateReq, DormDO.class);
        updateById(deptDO);
    }

    public List<DormVO> queryAll() {
        List<DormDO> list = list();
        return BeanUtil.copyToList(list, DormVO.class);
    }
}