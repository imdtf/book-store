package com.github.imdtf.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/12 22:36
 * 4
 */
@SpringBootApplication
@EnableCaching
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class MonolithicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonolithicApplication.class, args);
    }
}
