package com.github.imdtf.bookstore.domain.payment;

import org.springframework.data.repository.CrudRepository;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:25
 * 4
 */
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}
