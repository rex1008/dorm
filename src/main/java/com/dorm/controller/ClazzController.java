package com.dorm.controller;

import com.dorm.bean.req.ClazzAddReq;
import com.dorm.bean.req.ClazzPageReq;
import com.dorm.bean.req.ClazzUpdateReq;
import com.dorm.bean.vo.ClazzVO;
import com.dorm.bean.vo.PageVO;
import com.dorm.res.Result;
import com.dorm.service.ClazzService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/clazz")
@Validated
public class ClazzController {

    @Resource
    private ClazzService clazzService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated ClazzAddReq addReq) {
        clazzService.add(addReq);
        return Result.buildSuccess("添加成功");
    }

    @PostMapping("/queryPage")
    public Result<List<ClazzVO>> queryPage(@RequestBody @Validated ClazzPageReq pageReq) {
        PageVO<ClazzVO> pageVO = clazzService.queryPage(pageReq);
        return Result.buildSuccess(pageVO.getList(), pageVO.getTotal());
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@NotNull Long id) {
        clazzService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }

    @GetMapping("/queryById")
    public Result<ClazzVO> queryById(@NotNull Long id) {
        return Result.buildSuccess(clazzService.queryById(id));
    }

    @PostMapping("/modifyById")
    public Result<String> modifyById(@RequestBody @Validated ClazzUpdateReq updateReq) {
        clazzService.modifyById(updateReq);
        return Result.buildSuccess("修改成功");
    }

    @GetMapping("/queryAll")
    public Result<List<ClazzVO>> queryAll() {
        return Result.buildSuccess(clazzService.queryAll());
    }

    @GetMapping("/listByDeptId")
    public Result<List<ClazzVO>> listByDeptId(@NotNull Long deptId) {
        return Result.buildSuccess(clazzService.listByDeptId(deptId));
    }
}
