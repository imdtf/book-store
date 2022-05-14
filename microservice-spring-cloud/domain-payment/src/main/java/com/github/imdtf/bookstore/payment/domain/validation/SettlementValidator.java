package com.github.imdtf.bookstore.payment.domain.validation;

import com.github.imdtf.bookstore.dto.Settlement;
import com.github.imdtf.bookstore.payment.domain.client.ProductServiceClient;
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

    private final ProductServiceClient service;

    @Override
    public boolean isValid(Settlement settlement, ConstraintValidatorContext constraintValidatorContext) {
        return settlement.getItems().stream().noneMatch(i -> service.queryStockpile(i.getProductId()).getAmount() < i.getAmount());
    }
}
