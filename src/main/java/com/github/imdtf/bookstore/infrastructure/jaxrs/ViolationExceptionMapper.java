package com.github.imdtf.bookstore.infrastructure.jaxrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/14 22:43
 * 4
 */
@ControllerAdvice
public class ViolationExceptionMapper {
    private static final Logger log = LoggerFactory.getLogger(ViolationExceptionMapper.class);

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ResponseEntity<?> bizExceptionHandler(BaseException e) {
        log.error("发生业务异常！原因是：{}", e.getMessage());
        return getResponse(e);
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return getResponse(e);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(Exception e) {
        log.error("未知异常！原因是:", e);
        return getResponse(e);
    }

    private ResponseEntity<?> getResponse(Throwable t) {
        return CommonResponse.failure(t.getMessage());
    }
}
