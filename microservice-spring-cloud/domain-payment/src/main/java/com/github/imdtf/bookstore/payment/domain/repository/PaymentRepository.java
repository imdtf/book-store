package com.github.imdtf.bookstore.payment.domain.repository;

import com.github.imdtf.bookstore.payment.domain.Payment;
import org.springframework.data.repository.CrudRepository;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:25
 * 4
 */
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

    /**
     * 根据支付 id 查询
     *
     * @param payId 支付 id
     * @return 结果
     */
    Payment findByPayId(String payId);
}
