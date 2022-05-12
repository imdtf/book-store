package com.github.imdtf.bookstore.infrastructure.utility;

import com.github.imdtf.bookstore.domain.account.Account;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 0 *
 * 1 * @Author: DTF
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/5/12 22:11
 * 4
 */
public class SecurityUtil {

    public static Account getUser() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
