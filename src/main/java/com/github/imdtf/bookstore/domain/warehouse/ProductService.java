package com.github.imdtf.bookstore.domain.warehouse;

import com.github.imdtf.bookstore.application.payment.dto.Settlement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/18 22:36
 * 4
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public void replenishProductInformation(Settlement bill) {
        List<Integer> productIds = bill.getItems().stream().map(Settlement.Item::getProductId).collect(Collectors.toList());
        bill.setProductMap(repository.findByIdIn(productIds).stream().collect(Collectors.toMap(Product::getId, Function.identity())));
    }

    public Iterable<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProduct(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public void removeProduct(Integer id) {
        repository.deleteById(id);
    }
}
