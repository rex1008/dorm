package com.dorm.controller;

import com.dorm.bean.req.LoginReq;
import com.dorm.res.Result;
import com.dorm.service.ManagerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/manager")
@Validated
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated LoginReq loginReq) {
        return Result.buildSuccess(managerService.login(loginReq));
    }
}
