package com.dorm.controller;

import com.dorm.bean.req.DormAddReq;
import com.dorm.bean.req.DormPageReq;
import com.dorm.bean.req.DormUpdateReq;
import com.dorm.bean.vo.DormVO;
import com.dorm.bean.vo.PageVO;
import com.dorm.res.Result;
import com.dorm.service.DormService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/dorm")
@Validated
public class DormController {

    @Resource
    private DormService dormService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated DormAddReq addReq) {
        dormService.add(addReq);
        return Result.buildSuccess("添加成功");
    }

    @PostMapping("/queryPage")
    public Result<List<DormVO>> queryPage(@RequestBody @Validated DormPageReq pageReq) {
        PageVO<DormVO> pageVO = dormService.queryPage(pageReq);
        return Result.buildSuccess(pageVO.getList(), pageVO.getTotal());
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@NotNull Long id) {
        dormService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }

    @GetMapping("/queryById")
    public Result<DormVO> queryById(@NotNull Long id) {
        return Result.buildSuccess(dormService.queryById(id));
    }

    @PostMapping("/modifyById")
    public Result<String> modifyById(@RequestBody @Validated DormUpdateReq updateReq) {
        dormService.modifyById(updateReq);
        return Result.buildSuccess("修改成功");
    }

    @GetMapping("/queryAll")
    public Result<List<DormVO>> queryAll() {
        return Result.buildSuccess(dormService.queryAll());
    }
}
