package com.mydemo.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    /**
     * 前置
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("interceptor preHandle");
        return super.preHandle(request, response, handler);
    }

    /**
     * 后置
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("interceptor postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 完成后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("interceptor afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 如果接口返回一个current类型的变量，回信器用一个线程，执行了prehandle之后，执行此方法
     * 再一次执行prehandle....
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("interceptor afterConcurrentHandlingStarted");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
