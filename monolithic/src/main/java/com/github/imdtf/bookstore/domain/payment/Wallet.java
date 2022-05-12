package com.github.imdtf.bookstore.domain.payment;

import com.github.imdtf.bookstore.domain.BaseEntity;
import com.github.imdtf.bookstore.domain.account.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:45
 * 4
 */
@Entity
@Getter
@Setter
public class Wallet extends BaseEntity {

    private Double money;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
