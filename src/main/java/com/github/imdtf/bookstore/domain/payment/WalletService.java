package com.github.imdtf.bookstore.domain.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/19 22:45
 * 4
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {

    private WalletRepository repository;
}
