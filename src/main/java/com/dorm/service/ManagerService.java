package com.dorm.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dorm.bean.dto.TokenDTO;
import com.dorm.bean.entity.ManagerDO;
import com.dorm.bean.req.LoginReq;
import com.dorm.bean.req.ManagerUpdateReq;
import com.dorm.bean.vo.ManagerVO;
import com.dorm.ex.BizEx;
import com.dorm.mapper.ManagerMapper;
import com.dorm.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerService extends ServiceImpl<ManagerMapper, ManagerDO> {

    public String login(LoginReq loginReq) {
        ManagerDO one = getOne(Wrappers.<ManagerDO>lambdaQuery()
                .eq(ManagerDO::getUsername, loginReq.getUsername())
                .eq(ManagerDO::getPwd, loginReq.getPwd()));
        if (one == null) {
            throw new BizEx("用户名或密码不正确");
        }
        TokenDTO tokenDTO = BeanUtil.copyProperties(one, TokenDTO.class);
        return JwtUtil.getToken(tokenDTO);
    }

    public ManagerVO queryById(Long id) {
        ManagerDO one = getById(id);
        if (one == null) {
            throw new BizEx("用户不存在");
        }
        return BeanUtil.copyProperties(one, ManagerVO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyById(ManagerUpdateReq updateReq) {
        LambdaUpdateWrapper<ManagerDO> luw = Wrappers.<ManagerDO>lambdaUpdate()
                .set(ManagerDO::getRealName, updateReq.getRealName())
                .set(ManagerDO::getSex, updateReq.getSex())
                .set(ManagerDO::getAvatarPath, updateReq.getAvatarPath())
                .eq(ManagerDO::getId, updateReq.getId());
        update(luw);
    }
}