package com.dorm.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.dorm.bean.req.UploadFileReq;
import com.dorm.config.properties.UploadProperties;
import com.dorm.ex.BizEx;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class StorageService {

    @Resource
    private UploadProperties uploadProperties;

    public String upload(UploadFileReq uploadFileReq) {
        MultipartFile file = uploadFileReq.getFile();
        String originalFilename = file.getOriginalFilename();
        String module = uploadFileReq.getModule();
        // 拼接出相对路径
        String relativePath = spliceRelativePath(originalFilename, module);
        storeFile(relativePath, file);
        return relativePath;
    }

    private void storeFile(String relativePath, MultipartFile multipartFile) {
        String fullPath = uploadProperties.getRootPath() + relativePath;
        File file = new File(fullPath);
        try {
            // 再次确保目录被创建
            FileUtils.forceMkdir(file.getParentFile());
        } catch (IOException e) {
            throw new BizEx("创建目录出错");
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error("store file error. filePath:{}", fullPath, e);
            throw new BizEx("存储文件出错");
        }
    }

    private String spliceRelativePath(String originalFilename, String module) {
        StringBuilder s = new StringBuilder();
        s.append("resources/");
        if (StringUtils.isNotBlank(module)) {
            // 如果指定了模块路径
            s.append(module).append(StringPool.SLASH);
        }
        String nowDate = DateUtil.format(LocalDateTime.now(), DatePattern.PURE_DATE_PATTERN);
        String newFileName = reName(originalFilename);
        s.append(nowDate).append(StringPool.SLASH).append(newFileName);
        return s.toString();
    }

    private String reName(String originalFilename) {
        String uuId = IdUtil.fastSimpleUUID();
        String suffix = StringPool.DOT + FilenameUtils.getExtension(originalFilename);
        return uuId + suffix;
    }
}
