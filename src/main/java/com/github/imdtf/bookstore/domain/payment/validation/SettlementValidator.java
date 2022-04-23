package com.github.imdtf.bookstore.domain.payment.validation;

import com.github.imdtf.bookstore.application.payment.dto.Settlement;
import com.github.imdtf.bookstore.domain.payment.StockpileService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:38
 * 4
 */
@RequiredArgsConstructor
public class SettlementValidator implements ConstraintValidator<SufficientStock, Settlement> {

    private final StockpileService service;

    @Override
    public boolean isValid(Settlement settlement, ConstraintValidatorContext constraintValidatorContext) {
        return settlement.getItems().stream().noneMatch(i -> service.getByProductId(i.getProductId()).getAmount() < i.getAmount());
    }
}
