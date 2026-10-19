package com.dorm.controller;

import com.dorm.bean.req.StudentAddReq;
import com.dorm.bean.req.StudentPageReq;
import com.dorm.bean.req.StudentUpdateReq;
import com.dorm.bean.vo.StudentVO;
import com.dorm.bean.vo.PageVO;
import com.dorm.res.Result;
import com.dorm.service.StudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated StudentAddReq addReq) {
        studentService.add(addReq);
        return Result.buildSuccess("添加成功");
    }

    @PostMapping("/queryPage")
    public Result<List<StudentVO>> queryPage(@RequestBody @Validated StudentPageReq pageReq) {
        PageVO<StudentVO> pageVO = studentService.queryPage(pageReq);
        return Result.buildSuccess(pageVO.getList(), pageVO.getTotal());
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@NotNull Long id) {
        studentService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }

    @GetMapping("/queryById")
    public Result<StudentVO> queryById(@NotNull Long id) {
        return Result.buildSuccess(studentService.queryById(id));
    }

    @PostMapping("/modifyById")
    public Result<String> modifyById(@RequestBody @Validated StudentUpdateReq updateReq) {
        studentService.modifyById(updateReq);
        return Result.buildSuccess("修改成功");
    }
}
