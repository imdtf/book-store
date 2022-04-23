package com.github.imdtf.bookstore.domain.payment;

import com.github.imdtf.bookstore.application.payment.dto.Settlement;
import com.github.imdtf.bookstore.infrastructure.cache.CacheConfiguration;
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

    private final StockpileService stockpileService;

    private final PaymentRepository paymentRepository;

    @Resource(name = "settlement")
    private Cache settlementCache;

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
}
