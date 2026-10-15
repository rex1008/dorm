package com.dorm.config.properties;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@ConfigurationProperties(prefix = "upload")
@Data
public class UploadProperties {

    private String rootPath;

    @PostConstruct
    public void init() {
        if (StringUtils.isBlank(rootPath)) {
            throw new RuntimeException("UploadProperties.rootPath未配置");
        }
        if (!rootPath.endsWith(StringPool.SLASH)) {
            throw new RuntimeException("UploadProperties.rootPath没有以/结尾");
        }
        File file = new File(rootPath);
        if (!file.isAbsolute()) {
            throw new RuntimeException("UploadProperties.rootPath不是绝对路径");
        }
        if (!file.exists()) {
            try {
                FileUtils.forceMkdir(file);
            } catch (IOException e) {
                throw new RuntimeException("创建UploadProperties.rootPath目录出错");
            }
        }
    }
}
