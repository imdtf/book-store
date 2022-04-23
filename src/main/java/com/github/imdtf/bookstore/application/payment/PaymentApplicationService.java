package com.github.imdtf.bookstore.application.payment;

import com.github.imdtf.bookstore.application.payment.dto.Settlement;
import com.github.imdtf.bookstore.domain.payment.Payment;
import com.github.imdtf.bookstore.domain.payment.PaymentService;
import com.github.imdtf.bookstore.domain.payment.WalletService;
import com.github.imdtf.bookstore.domain.warehouse.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private final ProductService productService;

    private final WalletService walletService;

    public Payment executeBySettlement(Settlement bill) {
        productService.replenishProductInformation(bill);
        Payment payment = paymentService.producePayment(bill);
        paymentService.setupAutoThawedTrigger(payment);
        return payment;
    }
}
