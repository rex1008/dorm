package com.dorm.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dorm.bean.entity.DeptDO;
import com.dorm.bean.req.DeptAddReq;
import com.dorm.bean.req.DeptPageReq;
import com.dorm.bean.req.DeptUpdateReq;
import com.dorm.bean.vo.DeptVO;
import com.dorm.bean.vo.PageVO;
import com.dorm.ex.BizEx;
import com.dorm.mapper.DeptMapper;
import com.dorm.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptService extends ServiceImpl<DeptMapper, DeptDO> {

    @Resource
    private ClazzService clazzService;

    @Transactional(rollbackFor = Exception.class)
    public void add(DeptAddReq addReq) {
        DeptDO deptDO = BeanUtil.copyProperties(addReq, DeptDO.class);
        save(deptDO);
    }

    public PageVO<DeptVO> queryPage(DeptPageReq pageReq) {
        IPage<DeptDO> iPage = PageUtil.buildPage(pageReq.getPageIndex(), pageReq.getPageSize());
        LambdaQueryWrapper<DeptDO> lqw = Wrappers.<DeptDO>lambdaQuery()
                .like(StringUtils.isNotBlank(pageReq.getDeptName()), DeptDO::getDeptName, pageReq.getDeptName())
                .orderByDesc(DeptDO::getId);
        IPage<DeptDO> page = page(iPage, lqw);
        return PageVO.of(BeanUtil.copyToList(page.getRecords(), DeptVO.class), page.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        if (CollectionUtil.isNotEmpty(clazzService.listByDeptId(id))) {
            throw new BizEx("系别已被班级使用，无法删除");
        }
        removeById(id);
    }

    public DeptVO queryById(Long id) {
        DeptDO record = getById(id);
        return BeanUtil.copyProperties(record, DeptVO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyById(DeptUpdateReq updateReq) {
        DeptDO deptDO = BeanUtil.copyProperties(updateReq, DeptDO.class);
        updateById(deptDO);
    }

    public List<DeptVO> queryAll() {
        List<DeptDO> list = list();
        return BeanUtil.copyToList(list, DeptVO.class);
    }
}