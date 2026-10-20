package com.dorm.controller;

import com.dorm.bean.req.DormBuildingAddReq;
import com.dorm.bean.req.DormBuildingPageReq;
import com.dorm.bean.req.DormBuildingUpdateReq;
import com.dorm.bean.vo.DormBuildingVO;
import com.dorm.bean.vo.PageVO;
import com.dorm.res.Result;
import com.dorm.service.DormBuildingService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/dormBuilding")
@Validated
public class DormBuildingController {

    @Resource
    private DormBuildingService dormBuildingService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated DormBuildingAddReq addReq) {
        dormBuildingService.add(addReq);
        return Result.buildSuccess("添加成功");
    }

    @PostMapping("/queryPage")
    public Result<List<DormBuildingVO>> queryPage(@RequestBody @Validated DormBuildingPageReq pageReq) {
        PageVO<DormBuildingVO> pageVO = dormBuildingService.queryPage(pageReq);
        return Result.buildSuccess(pageVO.getList(), pageVO.getTotal());
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@NotNull Long id) {
        dormBuildingService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }

    @GetMapping("/queryById")
    public Result<DormBuildingVO> queryById(@NotNull Long id) {
        return Result.buildSuccess(dormBuildingService.queryById(id));
    }

    @PostMapping("/modifyById")
    public Result<String> modifyById(@RequestBody @Validated DormBuildingUpdateReq updateReq) {
        dormBuildingService.modifyById(updateReq);
        return Result.buildSuccess("修改成功");
    }

    @GetMapping("/queryAll")
    public Result<List<DormBuildingVO>> queryAll() {
        return Result.buildSuccess(dormBuildingService.queryAll());
    }
}
