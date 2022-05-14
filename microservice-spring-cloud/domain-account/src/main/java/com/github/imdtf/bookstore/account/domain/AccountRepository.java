package com.github.imdtf.bookstore.account.domain;

import com.github.imdtf.bookstore.domain.account.Account;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/12 23:06
 * 4
 */
@CacheConfig(cacheNames = "repository.account")
public interface AccountRepository extends CrudRepository<Account, Integer> {

    /**
     * 查询全部
     *
     * @return 结果
     */
    @Override
    Iterable<Account> findAll();

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 结果
     */
    @Cacheable(key = "#username")
    Account findByUsername(String username);

    /**
     * 保存
     *
     * @param entity 实体
     * @param <S>    泛型
     * @return 结果
     */
    <S extends Account> S save(S entity);

    /**
     * 判断根据用户名、邮箱、电话是否存在重复
     *
     * @param username  用户名
     * @param email     邮箱
     * @param telephone 电话
     * @return 结果
     */
    boolean existsByUsernameOrEmailOrTelephone(String username, String email, String telephone);

    /**
     * 判断根据用户名、邮箱、电话查找
     *
     * @param username  用户名
     * @param email     邮箱
     * @param telephone 电话
     * @return 结果
     */
    Collection<Account> findByUsernameOrEmailOrTelephone(String username, String email, String telephone);
}
