package com.mydemo.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

/**
 * 拦截器和过滤器的注册
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @PostConstruct
    private void init() {
        System.out.println("init webConfig");
    }

    /**
     * bean默认是单例的
     * @return
     */
    @Bean
    public AuthInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }

    /**
     * 增加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthInterceptor());
    }

    /**
     * 第一种
     */
//    @Bean
//    public MyFilter myFilter() {
//        return new MyFilter();
//    }

    /**
     * 第二种
     */
    @Bean
    public FilterRegistrationBean<MyFilter> filterFilterRegistrationBean() {
        FilterRegistrationBean<MyFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        // 过滤所有
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setFilter(new MyFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addInitParameter("initParam", "initOk");
        return filterFilterRegistrationBean;
    }

    /**
     * 第三种 直接在MyFilter类名上加WebFilter注解(使用详见WebFilter)
     * 注意添加 ServletComponentScan("......略")
     */
}
