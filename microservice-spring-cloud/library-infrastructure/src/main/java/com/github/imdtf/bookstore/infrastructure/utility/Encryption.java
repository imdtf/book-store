package com.github.imdtf.bookstore.infrastructure.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/12 22:58
 * 4
 */
@Configuration
public class Encryption {

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("create BCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    public String encoder(CharSequence rawPassword) {
        return passwordEncoder().encode(Optional.ofNullable(rawPassword).orElse(""));
    }
}
