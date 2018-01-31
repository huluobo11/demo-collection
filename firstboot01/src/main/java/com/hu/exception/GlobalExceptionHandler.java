package com.hu.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 二：在执行@RequestMapping时，进入逻辑处理阶段前。譬如传的参数类型错误。
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  /*  定义一个类，使用@ControllerAdvice注解，继承ResponseEntityExceptionHandler类，这个类里面实现了很多方法，可以去看看，包括一些参数转换，请求方法不支持等等之类的异常都会被捕获。
    被捕获的原因是@ExceptionHandler标签，里面所有的异常类只要发生了，就会被这个方法所捕获。*/
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("错误");

        return new ResponseEntity<Object>("出错了", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
