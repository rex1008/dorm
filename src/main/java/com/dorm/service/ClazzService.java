package com.dorm.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dorm.bean.entity.ClazzDO;
import com.dorm.bean.entity.DeptDO;
import com.dorm.bean.req.ClazzAddReq;
import com.dorm.bean.req.ClazzPageReq;
import com.dorm.bean.req.ClazzUpdateReq;
import com.dorm.bean.vo.ClazzVO;
import com.dorm.bean.vo.PageVO;
import com.dorm.ex.BizEx;
import com.dorm.mapper.ClazzMapper;
import com.dorm.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClazzService extends ServiceImpl<ClazzMapper, ClazzDO> {

    @Resource
    private DeptService deptService;
    @Resource
    private StudentService studentService;

    @Transactional(rollbackFor = Exception.class)
    public void add(ClazzAddReq addReq) {
        ClazzDO entity = BeanUtil.copyProperties(addReq, ClazzDO.class);
        save(entity);
    }

    public PageVO<ClazzVO> queryPage(ClazzPageReq pageReq) {
        IPage<ClazzDO> iPage = PageUtil.buildPage(pageReq.getPageIndex(), pageReq.getPageSize());
        LambdaQueryWrapper<ClazzDO> lqw = Wrappers.<ClazzDO>lambdaQuery()
                .like(StringUtils.isNotBlank(pageReq.getClazzNo()), ClazzDO::getClazzNo, pageReq.getClazzNo())
                .like(StringUtils.isNotBlank(pageReq.getClazzName()), ClazzDO::getClazzName, pageReq.getClazzName())
                .like(Objects.nonNull(pageReq.getDeptId()), ClazzDO::getDeptId, pageReq.getDeptId())
                .orderByDesc(ClazzDO::getId);
        IPage<ClazzDO> page = page(iPage, lqw);
        if (CollectionUtil.isEmpty(page.getRecords())) {
            return PageVO.of(ListUtil.empty(), 0L);
        }
        // 处理deptName属性
        Set<Long> deptIds = page.getRecords().stream().
                map(ClazzDO::getDeptId)
                .collect(Collectors.toSet());
        List<DeptDO> deptEntities = deptService.listByIds(deptIds);
        if (CollectionUtil.isEmpty(deptEntities)) {
            return PageVO.of(BeanUtil.copyToList(page.getRecords(), ClazzVO.class), page.getTotal());
        }
        Map<Long, String> deptNameMapping = deptEntities.stream()
                .collect(Collectors.toMap(DeptDO::getId, DeptDO::getDeptName, (a1, a2) -> a1));
        List<ClazzVO> resultList = page.getRecords().stream().map(record -> {
            ClazzVO clazzVO = BeanUtil.copyProperties(record, ClazzVO.class);
            clazzVO.setDeptName(deptNameMapping.get(clazzVO.getDeptId()));
            return clazzVO;
        }).collect(Collectors.toList());
        return PageVO.of(resultList, page.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        if (studentService.clazzUsed(id)) {
            throw new BizEx("班级下已有学生，无法删除");
        }
        removeById(id);
    }

    public ClazzVO queryById(Long id) {
        ClazzDO record = getById(id);
        return BeanUtil.copyProperties(record, ClazzVO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyById(ClazzUpdateReq updateReq) {
        ClazzDO entity = BeanUtil.copyProperties(updateReq, ClazzDO.class);
        updateById(entity);
    }

    public List<ClazzVO> queryAll() {
        List<ClazzDO> list = list();
        return BeanUtil.copyToList(list, ClazzVO.class);
    }

    public List<ClazzVO> listByDeptId(Long deptId) {
        LambdaQueryWrapper<ClazzDO> lqw = Wrappers.<ClazzDO>lambdaQuery()
                .eq(ClazzDO::getDeptId, deptId)
                .orderByDesc(ClazzDO::getId);
        return BeanUtil.copyToList(list(lqw), ClazzVO.class);
    }
}