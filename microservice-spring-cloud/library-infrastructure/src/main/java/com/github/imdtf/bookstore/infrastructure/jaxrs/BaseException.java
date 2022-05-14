package com.github.imdtf.bookstore.infrastructure.jaxrs;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/14 22:47
 * 4
 */
public class BaseException extends RuntimeException{

    public BaseException() {
        super();
    }

    public BaseException(String msg) {
        super(msg);
    }
}
