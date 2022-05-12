package com.github.imdtf.bookstore.resource;

import com.github.imdtf.bookstore.application.payment.PaymentApplicationService;
import com.github.imdtf.bookstore.application.payment.dto.Settlement;
import com.github.imdtf.bookstore.domain.auth.Role;
import com.github.imdtf.bookstore.domain.payment.Payment;
import com.github.imdtf.bookstore.domain.payment.validation.SufficientStock;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:18
 * 4
 */
@RestController
@RequestMapping("/restful/settlements")
@RequiredArgsConstructor
public class SettlementResource {

    private final PaymentApplicationService service;

    @PostMapping
    @RolesAllowed(Role.USER)
    public Payment executeSettlement(@Valid @SufficientStock @RequestBody Settlement settlement) {
        return service.executeBySettlement(settlement);
    }
}
