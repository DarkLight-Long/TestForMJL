package com.mydemo.demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class AdviceControllerConfig {

    /**
     * 异常捕捉
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler
    public String exceptionHandler(Exception ex, HttpServletRequest request) {
        return "error";
    }

}
