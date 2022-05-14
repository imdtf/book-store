package com.github.imdtf.bookstore.warehouse.Resource;

import com.github.imdtf.bookstore.warehouse.domain.Advertisement;
import com.github.imdtf.bookstore.warehouse.domain.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/18 22:22
 * 4
 */
@RestController
@RequestMapping("/restful/advertisements")
@RequiredArgsConstructor
public class Advertisements {

    private final AdvertisementRepository repository;

    @GetMapping
    @Cacheable("resource.advertisements")
    public Iterable<Advertisement> getAllAdvertisements() {
        return repository.findAll();
    }
}
