package com.dorm.controller;

import com.dorm.bean.req.LoginReq;
import com.dorm.bean.req.ManagerUpdateReq;
import com.dorm.bean.vo.ManagerVO;
import com.dorm.res.Result;
import com.dorm.service.ManagerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

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

    @GetMapping("/queryById")
    public Result<ManagerVO> queryById(@NotNull Long id) {
        return Result.buildSuccess(managerService.queryById(id));
    }

    @PostMapping("/modifyById")
    public Result<String> modifyById(@RequestBody @Validated ManagerUpdateReq managerUpdateReq) {
        managerService.modifyById(managerUpdateReq);
        return Result.buildSuccess("修改成功");
    }
}
