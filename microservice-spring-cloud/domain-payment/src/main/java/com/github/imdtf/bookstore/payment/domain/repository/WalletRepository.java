package com.github.imdtf.bookstore.payment.domain.repository;

import com.github.imdtf.bookstore.payment.domain.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:46
 * 4
 */
public interface WalletRepository extends CrudRepository<Wallet, Integer> {

    /**
     * 根据用户 id 查询钱包
     *
     * @param accountId 用户 id
     * @return 结果
     */
    Optional<Wallet> findByAccountId(Integer accountId);
}
