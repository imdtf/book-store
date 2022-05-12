package com.github.imdtf.bookstore.domain.auth;

import com.github.imdtf.bookstore.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/15 22:54
 * 4
 */
@Component
@RequiredArgsConstructor
public class AuthenticAccountRepository {

    private final AccountRepository accountRepository;

    public AuthenticAccount findByUsername(String username) {
        return new AuthenticAccount(Optional.ofNullable(accountRepository.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException(String.format("user: %s is not existed", username))));
    }
}
