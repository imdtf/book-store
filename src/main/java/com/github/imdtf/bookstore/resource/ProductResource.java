package com.github.imdtf.bookstore.resource;

import com.github.imdtf.bookstore.application.ProductApplicationService;
import com.github.imdtf.bookstore.domain.auth.Role;
import com.github.imdtf.bookstore.domain.warehouse.Product;
import com.github.imdtf.bookstore.infrastructure.jaxrs.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/18 22:35
 * 4
 */
@RestController
@RequestMapping("/restful/products")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "resource.product")
public class ProductResource {

    private final ProductApplicationService service;

    @GetMapping
    @Cacheable(key = "'ALL_PRODUCT'")
    public Iterable<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public Product getProduct(@PathVariable Integer id) {
        return service.getProduct(id);
    }

    @PutMapping
    @Caching(evict = {
            @CacheEvict(key = "#product.id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    @RolesAllowed(Role.ADMIN)
    public Object updateProduct(@Valid Product product) {
        return CommonResponse.op(() -> service.saveProduct(product));
    }

    @PostMapping
    @Caching(evict = {
            @CacheEvict(key = "#product.id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    @RolesAllowed(Role.ADMIN)
    public Object createProduct(@Valid Product product) {
        return CommonResponse.op(() -> service.saveProduct(product));
    }

    @DeleteMapping("/{id}")
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    @RolesAllowed(Role.ADMIN)
    public Object removeProduct(@PathVariable Integer id) {
        return CommonResponse.op(() -> service.removeProduct(id));
    }

    @PatchMapping("/stockpile/{productId}")
    @RolesAllowed(Role.ADMIN)
    public Object updateStockpile(@PathVariable Integer productId, @RequestParam("amount") Integer amount) {
        // TODO
        // return CommonResponse.op(() -> service)
        return "todo";
    }

    @GetMapping("/stockpile/{productId}")
    @RolesAllowed(Role.ADMIN)
    public Object queryStockpile(@PathVariable Integer productId) {
        // TODO
        // return CommonResponse.op(() -> service)
        return "todo";
    }
}
