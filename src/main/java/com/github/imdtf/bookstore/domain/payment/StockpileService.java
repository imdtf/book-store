package com.github.imdtf.bookstore.domain.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:31
 * 4
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StockpileService {

    private final StockpileRepository repository;

    public Stockpile getByProductId(Integer productId) {
        return repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
    }

    public void decrease(Integer productId, Integer amount) {
        Stockpile stock = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
        stock.decrease(amount);
        repository.save(stock);
        log.info("商品出库, 商品: {}, 数量: {}", productId, amount);
    }

    public void increase(Integer productId, Integer amount) {
        Stockpile stock = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
        stock.increase(amount);
        repository.save(stock);
        log.info("商品入库, 商品: {}, 数量: {}", productId, amount);
    }

    public void frozen(Integer productId, Integer amount) {
        Stockpile stock = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
        stock.frozen(amount);
        repository.save(stock);
        log.info("冻结库存, 商品: {}, 数量: {}", productId, amount);
    }

    public void thawed(Integer productId, Integer amount) {
        Stockpile stock = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
        stock.thawed(amount);
        repository.save(stock);
        log.info("解冻库存, 商品: {}, 数量: {}", productId, amount);
    }

    public void set(Integer productId, Integer amount) {
        Stockpile stock = repository.findById(productId).orElseThrow(() -> new EntityNotFoundException(productId.toString()));
        stock.setAmount(amount);
        repository.save(stock);
    }
}
