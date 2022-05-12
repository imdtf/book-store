package com.github.imdtf.bookstore.infrastructure.jaxrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/14 22:10
 * 4
 */
public class CommonResponse {

    private static final Logger log = LoggerFactory.getLogger(CommonResponse.class);

    public static ResponseEntity<?> send(HttpStatus status, String message) {
        Integer code = status == HttpStatus.OK ? CodedMessage.CODE_SUCCESS : CodedMessage.CODE_DEFAULT_FAILURE;
        return new ResponseEntity<>(new CodedMessage(code, message), status);
    }

    public static ResponseEntity<?> failure(String message) {
        return send(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static ResponseEntity<?> success(String message) {
        return send(HttpStatus.OK, message);
    }

    public static ResponseEntity<?> success() {
        return send(HttpStatus.OK, "operate success");
    }

    public static ResponseEntity<?> op(Runnable executor) {
        return op(executor, e -> log.error(e.getMessage(), e));
    }

    public static ResponseEntity<?> op(Runnable executor, Consumer<Exception> exceptionConsumer) {
        try {
            executor.run();
            return CommonResponse.success();
        } catch (Exception e) {
            exceptionConsumer.accept(e);
            return CommonResponse.failure(e.getMessage());
        }
    }
}
