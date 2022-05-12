package com.github.imdtf.bookstore.application;

import com.github.imdtf.bookstore.domain.account.Account;
import com.github.imdtf.bookstore.domain.account.AccountRepository;
import com.github.imdtf.bookstore.infrastructure.utility.Encryption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/12 22:55
 * 4
 */
@Component
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class AccountApplicationService {

    private final AccountRepository repository;

    private final Encryption encryption;

    public void createAccount(Account account) {
        account.setPassword(encryption.encoder(account.getPassword()));
        repository.save(account);
    }

    public Account findAccountByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void updateAccount(Account account) {
        repository.save(account);
    }
}
