package com.github.imdtf.bookstore.application;

import com.github.imdtf.bookstore.domain.payment.Stockpile;
import com.github.imdtf.bookstore.domain.payment.StockpileService;
import com.github.imdtf.bookstore.domain.warehouse.Product;
import com.github.imdtf.bookstore.domain.warehouse.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/18 22:35
 * 4
 */
@Component
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ProductApplicationService {

    private final ProductService service;

    private final StockpileService stockpileService;

    public Iterable<Product> getAllProducts() {
        return service.getAllProducts();
    }

    public Product getProduct(Integer id) {
        return service.getProduct(id);
    }

    public Product saveProduct(Product product) {
        return service.saveProduct(product);
    }

    public void removeProduct(Integer id) {
        service.removeProduct(id);
    }

    public Stockpile getStockpile(Integer productId) {
        return stockpileService.getByProductId(productId);
    }

    public void setStockpileAmountByProductId(Integer productId, Integer amount) {
        stockpileService.set(productId, amount);
    }
}
