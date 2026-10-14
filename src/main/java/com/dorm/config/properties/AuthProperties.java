package com.dorm.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "auth")
@Data
public class AuthProperties {

    private List<String> whiteList;
}
