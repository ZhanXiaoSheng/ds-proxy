package com.dscomm.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProxyWenzhouApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyWenzhouApplication.class, args);
    }

}
