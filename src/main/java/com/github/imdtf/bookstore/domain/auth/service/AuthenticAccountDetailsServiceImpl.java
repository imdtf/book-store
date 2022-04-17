package com.github.imdtf.bookstore.domain.auth.service;

import com.github.imdtf.bookstore.domain.auth.AuthenticAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/15 22:53
 * 4
 */
@Service
@RequiredArgsConstructor
public class AuthenticAccountDetailsServiceImpl implements UserDetailsService {

    private final AuthenticAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }
}
