package com.dorm.bean.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class UploadFileReq {

    @NotNull
    private MultipartFile file;

    private String module;
}
