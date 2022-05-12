package com.github.imdtf.bookstore.resource;

import com.github.imdtf.bookstore.application.payment.PaymentApplicationService;
import com.github.imdtf.bookstore.domain.account.Account;
import com.github.imdtf.bookstore.domain.payment.Payment;
import com.github.imdtf.bookstore.infrastructure.jaxrs.CommonResponse;
import com.github.imdtf.bookstore.infrastructure.utility.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/5/12 22:07
 * 4
 */

@RestController
@RequestMapping("/restful/pay")
@RequiredArgsConstructor
public class PaymentResource {

    private final PaymentApplicationService service;

    @PatchMapping("/{payId}")
    // @RolesAllowed(Role.USER)
    public Object updatePaymentState(@PathVariable("payId") String payId, @RequestParam("state") Payment.State state) {
        // TODO deal permission
        Account account = SecurityUtil.getUser();
        return updatePaymentStateAlias(payId, account.getId(), state);
    }

    @GetMapping("/modify/{payId}")
    public Object updatePaymentStateAlias(@PathVariable("payId") String payId, @RequestParam("accountId") Integer accountId, @RequestParam("state") Payment.State state) {
        if (state == Payment.State.PAYED) {
            return CommonResponse.op(() -> service.accomplishPayment(accountId, payId));
        } else {
            return CommonResponse.op(() -> service.cancelPayment(payId));
        }
    }
}
