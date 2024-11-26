package com.dscomm.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangsheng
 * Time 2024/10/28 10:53
 */
@Configuration
public class WebConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    // 其他配置，例如视图解析器、拦截器等
}
