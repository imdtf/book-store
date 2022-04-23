package com.github.imdtf.bookstore.domain.payment;

import com.github.imdtf.bookstore.domain.BaseEntity;
import com.github.imdtf.bookstore.domain.account.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Entity;
import java.util.Date;
import java.util.UUID;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:20
 * 4
 */
@Entity
@Getter
@Setter
public class Payment extends BaseEntity {

    public enum State {
        /**
         * 支付状态
         */
        WATTING,
        CANCEL,
        PAYED,
        TIMEOUT
    }

    private Date createTime;

    private String payId;

    private Double totalPrice;

    private Long expires;

    private String paymentLink;

    private State payState;

    public Payment() {
    }

    public Payment(Double totalPrice, Long expires) {
        setTotalPrice(totalPrice);
        setExpires(expires);
        setCreateTime(new Date());
        setPayState(State.WATTING);
        // TODO real pay id
        setPayId(UUID.randomUUID().toString());
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        setPaymentLink("/pay/modify/" + getPayId() + "?state=PAYED&accountId=" + account.getId());
    }


}
