package com.github.imdtf.bookstore.domain.warehouse;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/18 22:36
 * 4
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

    /**
     * 根据 id 列表查找产品
     *
     * @param ids id 列表
     * @return 结果
     */
    Collection<Product> findByIdIn(Collection<Integer> ids);
}
