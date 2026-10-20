package com.dorm.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dorm.bean.entity.DormBuildingDO;
import com.dorm.bean.req.DormBuildingAddReq;
import com.dorm.bean.req.DormBuildingPageReq;
import com.dorm.bean.req.DormBuildingUpdateReq;
import com.dorm.bean.vo.DormBuildingVO;
import com.dorm.bean.vo.PageVO;
import com.dorm.mapper.DormBuildingMapper;
import com.dorm.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DormBuildingService extends ServiceImpl<DormBuildingMapper, DormBuildingDO> {

    @Transactional(rollbackFor = Exception.class)
    public void add(DormBuildingAddReq addReq) {
        DormBuildingDO deptDO = BeanUtil.copyProperties(addReq, DormBuildingDO.class);
        save(deptDO);
    }

    public PageVO<DormBuildingVO> queryPage(DormBuildingPageReq pageReq) {
        IPage<DormBuildingDO> iPage = PageUtil.buildPage(pageReq.getPageIndex(), pageReq.getPageSize());
        LambdaQueryWrapper<DormBuildingDO> lqw = Wrappers.<DormBuildingDO>lambdaQuery()
                .like(StringUtils.isNotBlank(pageReq.getDormBuildingNo()), DormBuildingDO::getDormBuildingNo, pageReq.getDormBuildingNo())
                .like(StringUtils.isNotBlank(pageReq.getDormBuildingName()), DormBuildingDO::getDormBuildingName, pageReq.getDormBuildingName())
                .orderByDesc(DormBuildingDO::getId);
        IPage<DormBuildingDO> page = page(iPage, lqw);
        return PageVO.of(BeanUtil.copyToList(page.getRecords(), DormBuildingVO.class), page.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
//        if (CollectionUtil.isNotEmpty(clazzService.listByDormBuildingId(id))) {
//            throw new BizEx("系别已被班级使用，无法删除");
//        }
        removeById(id);
    }

    public DormBuildingVO queryById(Long id) {
        DormBuildingDO record = getById(id);
        return BeanUtil.copyProperties(record, DormBuildingVO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyById(DormBuildingUpdateReq updateReq) {
        DormBuildingDO deptDO = BeanUtil.copyProperties(updateReq, DormBuildingDO.class);
        updateById(deptDO);
    }

    public List<DormBuildingVO> queryAll() {
        List<DormBuildingDO> list = list();
        return BeanUtil.copyToList(list, DormBuildingVO.class);
    }
}