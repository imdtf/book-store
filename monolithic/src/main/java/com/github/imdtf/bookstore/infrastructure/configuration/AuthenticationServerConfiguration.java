package com.github.imdtf.bookstore.infrastructure.configuration;

import com.github.imdtf.bookstore.domain.auth.provider.PreAuthenticatedAuthenticationProvider;
import com.github.imdtf.bookstore.domain.auth.provider.UsernamePasswordAuthenticationProvider;
import com.github.imdtf.bookstore.domain.auth.service.AuthenticAccountDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/15 23:00
 * 4
 */
@Configuration
@RequiredArgsConstructor
public class AuthenticationServerConfiguration extends WebSecurityConfiguration {

    private final AuthenticAccountDetailsServiceImpl accountDetailsService;

    private final UsernamePasswordAuthenticationProvider userProvider;

    private final PreAuthenticatedAuthenticationProvider preProvider;

    private final PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(userProvider);
        auth.authenticationProvider(preProvider);
    }
}
