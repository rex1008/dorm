package com.dorm.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dorm.bean.dto.TokenDTO;
import com.dorm.bean.entity.ManagerDO;
import com.dorm.bean.req.LoginReq;
import com.dorm.ex.BizEx;
import com.dorm.mapper.ManagerMapper;
import com.dorm.util.JwtUtil;
import org.springframework.stereotype.Service;

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
}