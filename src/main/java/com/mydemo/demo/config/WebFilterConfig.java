package com.mydemo.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器注册
 */
@Configuration
public class WebFilterConfig {

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
