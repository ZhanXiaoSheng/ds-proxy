package com.dscomm.proxy.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangsheng
 * Time 2024/10/29 9:30
 * 缓存服务 若有硬件资源，可考虑使用redis
 */
@Service
@Slf4j
public class CacheService {
    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        if(Strings.isBlank(key)){
            log.error("key is blank");
            return "null";
        }
        log.info(String.format("get cache : key=[%s],value=[%s]", key,cache.get(key)));
        return cache.get(key);
    }

    // 可以根据需要添加更多的方法
}
