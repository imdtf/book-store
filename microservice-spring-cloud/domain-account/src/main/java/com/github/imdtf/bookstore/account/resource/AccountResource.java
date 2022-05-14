package com.github.imdtf.bookstore.account.resource;

import com.github.imdtf.bookstore.account.application.AccountApplicationService;
import com.github.imdtf.bookstore.domain.account.Account;
import com.github.imdtf.bookstore.infrastructure.jaxrs.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/5/14 12:19
 * 4
 */
@RestController
@RequestMapping("/restful/accounts")
@CacheConfig(cacheNames = "resource.account")
@RequiredArgsConstructor
public class AccountResource {

    private final AccountApplicationService service;

    @GetMapping("/{username}")
    public Account getUser(@PathVariable("username") String username) {
        return service.findAccountByUsername(username);
    }

    @PostMapping
    @CacheEvict(key = "#user.username")
    public Object createUser(@RequestBody Account user) {
        return CommonResponse.op(() -> service.createAccount(user));
    }

    @PutMapping
    @CacheEvict(key = "#user.username")
    public Object updateUser(@RequestBody Account user) {
        return CommonResponse.op(() -> service.updateAccount(user));
    }
}
