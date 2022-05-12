package com.github.imdtf.bookstore.domain.account.validation;

import com.github.imdtf.bookstore.domain.account.Account;
import com.github.imdtf.bookstore.domain.account.AccountRepository;
import com.github.imdtf.bookstore.domain.auth.AuthenticAccount;
import com.github.imdtf.bookstore.infrastructure.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/12 23:13
 * 4
 */
public class AccountValidation<T extends Annotation> implements ConstraintValidator<T, Account> {

    @Autowired
    protected AccountRepository repository;

    protected Predicate<Account> predicate = c -> true;

    @Override
    public boolean isValid(Account account, ConstraintValidatorContext constraintValidatorContext) {
        return repository == null || predicate.test(account);
    }

    public static class ExistsAccountValidator extends AccountValidation<ExistsAccount> {

        @Override
        public void initialize(ExistsAccount constraintAnnotation) {
            predicate = c -> repository.existsById(c.getId());
        }
    }

    public static class AuthenticatedAccountValidator extends AccountValidation<AuthenticatedAccount> {

        @Override
        public void initialize(AuthenticatedAccount constraintAnnotation) {
            predicate = c -> {
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (Constant.ANONYMOUS_USER.equals(principal)) {
                    return false;
                } else {
                    AuthenticAccount loginUser = (AuthenticAccount) principal;
                    return c.getId().equals(loginUser.getId());
                }
            };
        }
    }

    public static class UniqueAccountValidator extends AccountValidation<UniqueAccount> {

        @Override
        public void initialize(UniqueAccount constraintAnnotation) {
            predicate = c -> !repository.existsByUsernameOrEmailOrTelephone(c.getUsername(), c.getEmail(), c.getTelephone());
        }
    }

    public static class NoConflictAccountValidator extends AccountValidation<NotConflictAccount> {

        @Override
        public void initialize(NotConflictAccount notConflictAccount) {
            predicate = c -> {
                Collection<Account> collection = repository.findByUsernameOrEmailOrTelephone(c.getUsername(), c.getEmail(), c.getTelephone());
                return collection.isEmpty() || (collection.size() == 1 && collection.iterator().next().getId().equals(c.getId()));
            };
        }
    }
}
