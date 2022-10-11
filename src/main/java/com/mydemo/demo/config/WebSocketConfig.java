package com.mydemo.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * webSocket配置
 */
@Configuration
public class WebSocketConfig {

    /**
     * 此bean会自动注册@ServerEndpoint注解声明的Websocket endpoint
     * 否则webSocket不生效
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
