package com.github.imdtf.bookstore.payment.resource;

import com.github.imdtf.bookstore.domain.security.Role;
import com.github.imdtf.bookstore.dto.Settlement;
import com.github.imdtf.bookstore.payment.application.PaymentApplicationService;
import com.github.imdtf.bookstore.payment.domain.Payment;
import com.github.imdtf.bookstore.payment.domain.validation.SufficientStock;
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
