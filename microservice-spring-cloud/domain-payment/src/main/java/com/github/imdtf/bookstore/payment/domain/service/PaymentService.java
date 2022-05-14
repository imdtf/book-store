package com.github.imdtf.bookstore.payment.domain.service;

import com.github.imdtf.bookstore.dto.Settlement;
import com.github.imdtf.bookstore.infrastructure.cache.CacheConfiguration;
import com.github.imdtf.bookstore.payment.domain.Payment;
import com.github.imdtf.bookstore.payment.domain.client.ProductServiceClient;
import com.github.imdtf.bookstore.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:25
 * 4
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private static final long DEFAULT_PRODUCT_FROZEN_EXPIRES = CacheConfiguration.SYSTEM_DEFAULT_EXPIRES / 2;

    private final Timer timer = new Timer();

    private final ProductServiceClient stockpileService;

    private final PaymentRepository paymentRepository;

    @Resource(name = "settlement")
    private Cache settlementCache;

    public Payment findByPayId(String payId) {
        return paymentRepository.findByPayId(payId);
    }

    public Payment producePayment(Settlement bill) {
        Double total = bill.getItems().stream().mapToDouble(i -> {
            stockpileService.frozen(i.getProductId(), i.getAmount());
            return bill.getProductMap().get(i.getProductId()).getPrice() * i.getAmount();
        }).sum() + 12;
        Payment payment = new Payment(total, DEFAULT_PRODUCT_FROZEN_EXPIRES);
        paymentRepository.save(payment);
        settlementCache.put(payment.getPayId(), bill);
        log.info("创建支付订单, 总额: {}", payment.getTotalPrice());
        return payment;
    }

    public void setupAutoThawedTrigger(Payment payment) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (payment.getPayId().intern()) {
                    Payment currentPayment = paymentRepository.findById(payment.getId()).orElseThrow(() -> new EntityNotFoundException(payment.getId().toString()));
                    if (currentPayment.getPayState() == Payment.State.WATTING) {
                        log.info("支付单{}当前状态为: WAITING, 转变为: TIMEOUT", payment.getId());
                        accomplishSettlement(Payment.State.TIMEOUT, payment.getPayId());
                    }
                }
            }
        }, payment.getExpires());
    }

    private void accomplishSettlement(Payment.State endState, String payId) {
        Settlement settlement = (Settlement) Objects.requireNonNull(Objects.requireNonNull(settlementCache.get(payId)).get());
        settlement.getItems().forEach(i -> {
            if (endState == Payment.State.PAYED) {
                stockpileService.decrease(i.getProductId(), i.getAmount());
            } else {
                stockpileService.thawed(i.getProductId(), i.getAmount());
            }
        });
    }

    /**
     * 完成支付单，应该有第三方支付平台回调
     * @param payment 支付
     */
    public void accomplish(Payment payment) {
        String payId = payment.getPayId();
        synchronized (payId.intern()) {
            if (payment.getPayState() == Payment.State.WATTING) {
                payment.setPayState(Payment.State.PAYED);
                paymentRepository.save(payment);
                accomplishSettlement(Payment.State.PAYED, payId);
                log.info("编号为 {} 的支付单处理完成", payId);
            } else {
                throw new UnsupportedOperationException("当前订单不允许支付，当前状态为: " + payment.getPayState());
            }
        }
    }
    public void cancel(String payId) {
        synchronized (payId.intern()) {
            Payment payment = paymentRepository.findByPayId(payId);
            if (payment.getPayState() == Payment.State.WATTING) {
                payment.setPayState(Payment.State.CANCEL);
                paymentRepository.save(payment);
                accomplishSettlement(Payment.State.CANCEL, payId);
                log.info("编号为 {} 的支付取消支付", payId);
            } else {
                throw new UnsupportedOperationException("当前订单不允许取消，当前状态为: " + payment.getPayState());
            }
        }
    }
}
