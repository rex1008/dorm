package com.dorm.controller;

import com.dorm.bean.req.DeptAddReq;
import com.dorm.bean.req.DeptPageReq;
import com.dorm.bean.req.DeptUpdateReq;
import com.dorm.bean.vo.DeptVO;
import com.dorm.bean.vo.PageVO;
import com.dorm.res.Result;
import com.dorm.service.DeptService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/dept")
@Validated
public class DeptController {

    @Resource
    private DeptService deptService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated DeptAddReq addReq) {
        deptService.add(addReq);
        return Result.buildSuccess("添加成功");
    }

    @PostMapping("/queryPage")
    public Result<List<DeptVO>> queryPage(@RequestBody @Validated DeptPageReq pageReq) {
        PageVO<DeptVO> pageVO = deptService.queryPage(pageReq);
        return Result.buildSuccess(pageVO.getList(), pageVO.getTotal());
    }

    @PostMapping("/deleteById")
    public Result<String> deleteById(@NotNull Long id) {
        deptService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }

    @GetMapping("/queryById")
    public Result<DeptVO> queryById(@NotNull Long id) {
        return Result.buildSuccess(deptService.queryById(id));
    }

    @PostMapping("/modifyById")
    public Result<String> modifyById(@RequestBody @Validated DeptUpdateReq updateReq) {
        deptService.modifyById(updateReq);
        return Result.buildSuccess("修改成功");
    }

    @GetMapping("/queryAll")
    public Result<List<DeptVO>> queryAll() {
        return Result.buildSuccess(deptService.queryAll());
    }
}
