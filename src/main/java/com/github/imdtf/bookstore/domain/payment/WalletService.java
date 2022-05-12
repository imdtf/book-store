package com.github.imdtf.bookstore.domain.payment;

import com.github.imdtf.bookstore.domain.account.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:45
 * 4
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {

    private final WalletRepository repository;

    public void decrease(Integer accountId, Double amount) {
        Wallet wallet = repository.findByAccountId(accountId).orElseGet(() -> {
            Wallet newWallet = new Wallet();
            Account account = new Account();
            account.setId(accountId);
            newWallet.setMoney(0D);
            newWallet.setAccount(account);
            repository.save(newWallet);
            return newWallet;
        });
        if (wallet.getMoney() > amount) {
            wallet.setMoney(wallet.getMoney() - amount);
            repository.save(wallet);
            log.info("支付成功, 本次消费: {}, 用户余额: {}", amount, wallet.getMoney());
        } else {
            throw new RuntimeException("用户余额不足，请先充值");
        }
    }
}
