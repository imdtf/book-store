package com.github.imdtf.bookstore.domain.warehouse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    // TODO
    // public void replenishProductInformation()

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
