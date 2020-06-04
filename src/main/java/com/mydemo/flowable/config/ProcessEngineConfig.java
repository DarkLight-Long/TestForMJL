package com.mydemo.flowable.config;

import lombok.Data;
import org.flowable.common.engine.impl.AbstractEngineConfiguration;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 流程引擎配置文件
 * @author: linjinp
 * @create: 2019-10-21 16:49
 **/
@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class ProcessEngineConfig {

    private Logger logger = LoggerFactory.getLogger(ProcessEngineConfig.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

//    @Value("${spring.datasource.publicKey}")
//    private String publicKey;

    /**
     * 初始化流程引擎
     * @return
     */
    @Primary
    @Bean(name = "processEngine")
    public ProcessEngine initProcessEngine() {
        logger.info("=============================ProcessEngineBegin=============================");

        // 流程引擎配置
        ProcessEngineConfiguration cfg = null;

        try {
            cfg = new StandaloneProcessEngineConfiguration()
                    .setJdbcUrl(url)
                    .setJdbcUsername(username)
//                    .setJdbcPassword(ConfigTools.decrypt(publicKey, password))
                    .setJdbcPassword(password)
                    .setJdbcDriver(driverClassName)
                    // 初始化基础表，不需要的可以改为 DB_SCHEMA_UPDATE_FALSE
                    .setDatabaseSchemaUpdate(AbstractEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                    // 默认邮箱配置
                    // 发邮件的主机地址，先用 QQ 邮箱
                    .setMailServerHost("smtp.qq.com")
                    // POP3/SMTP服务的授权码
                    .setMailServerPassword("hergynqfusnsbbdf")
                    // 默认发件人
                    .setMailServerDefaultFrom("836369078@qq.com")
                    // 设置发件人用户名
                    .setMailServerUsername("管理员");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 初始化流程引擎对象
        ProcessEngine processEngine = cfg.buildProcessEngine();
        logger.info("=============================ProcessEngineEnd=============================");
        return processEngine;
    }
}
