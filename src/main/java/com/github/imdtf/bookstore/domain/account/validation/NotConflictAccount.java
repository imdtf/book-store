package com.github.imdtf.bookstore.domain.account.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/14 22:04
 * 4
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
@Constraint(validatedBy = AccountValidation.NoConflictAccountValidator.class)
public @interface NotConflictAccount {

    String message() default "username/email/telephone is same with existed account";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
