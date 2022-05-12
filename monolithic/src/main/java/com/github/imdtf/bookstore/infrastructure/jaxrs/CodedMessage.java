package com.github.imdtf.bookstore.infrastructure.jaxrs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/14 22:13
 * 4
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodedMessage {

    public static final Integer CODE_SUCCESS = 0;

    public static final Integer CODE_DEFAULT_FAILURE = 1;

    private Integer code;

    private String message;

    private Object data;

    public CodedMessage(Integer code, String message) {
       setCode(code);
       setMessage(message);
    }
}
