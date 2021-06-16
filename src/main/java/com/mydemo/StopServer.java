package com.mydemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关闭项目
 */
@RestController("/server")
@Slf4j
public class StopServer implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @GetMapping("/shutDown")
    public void shutDownServer() {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;
        configurableApplicationContext.close();
    }

}
