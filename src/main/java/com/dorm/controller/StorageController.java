package com.dorm.controller;

import com.dorm.bean.req.UploadFileReq;
import com.dorm.res.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/storage")
public class StorageController {

    @PostMapping("/uploadFile")
    public Result<String> uploadFile(@Validated UploadFileReq uploadFileReq) {
        return Result.buildSuccess("UP");
    }
}
