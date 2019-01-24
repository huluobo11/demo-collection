package com.hu.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandlerException(HttpServletRequest request, Exception e){
        e.printStackTrace();
        System.out.println("全局异常处理！");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/404.html");
        return modelAndView;
    }
}
