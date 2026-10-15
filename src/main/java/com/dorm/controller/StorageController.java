package com.dorm.controller;

import com.dorm.bean.req.UploadFileReq;
import com.dorm.res.Result;
import com.dorm.service.StorageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/storage")
@Validated
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("/uploadFile")
    public Result<String> uploadFile(@Validated UploadFileReq uploadFileReq) {
        String path = storageService.upload(uploadFileReq);
        return Result.buildSuccess(path);
    }
}
