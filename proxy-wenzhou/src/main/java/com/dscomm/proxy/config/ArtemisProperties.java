package com.dscomm.proxy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangsheng
 * Time 2024/10/28 12:02
 * artemis 配置类
 */
@Component
@ConfigurationProperties(prefix = "artemis")
@Data
public class ArtemisProperties {
    private String host;
    private String appKey;
    private String appSecret;
}
