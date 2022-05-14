package com.github.imdtf.bookstore.payment.application;

import com.github.imdtf.bookstore.dto.Settlement;
import com.github.imdtf.bookstore.payment.domain.Payment;
import com.github.imdtf.bookstore.payment.domain.client.ProductServiceClient;
import com.github.imdtf.bookstore.payment.domain.service.PaymentService;
import com.github.imdtf.bookstore.payment.domain.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:19
 * 4
 */
@Service
@RequiredArgsConstructor
public class PaymentApplicationService {

    private final PaymentService paymentService;

    private final ProductServiceClient productService;

    private final WalletService walletService;

    @Resource(name = "settlement")
    private Cache settlementCache;

    public Payment executeBySettlement(Settlement bill) {
        productService.replenishProductInformation(bill);
        Payment payment = paymentService.producePayment(bill);
        paymentService.setupAutoThawedTrigger(payment);
        return payment;
    }

    public void accomplishPayment(Integer accountId, String payId) {

        Payment payment = paymentService.findByPayId(payId);

        walletService.decrease(accountId, payment.getTotalPrice());

        paymentService.accomplish(payment);

        settlementCache.evict(payId);
    }

    public void cancelPayment(String payId) {
        paymentService.cancel(payId);

        settlementCache.evict(payId);
    }
}
