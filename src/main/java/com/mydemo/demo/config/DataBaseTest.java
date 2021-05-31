package com.mydemo.demo.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class DataBaseTest {

    private static String fileAddress = "./src/main/resources/DataBase.yml";
    private static DataSource dataSource =  null;

    // 读取配置文件
    @PostConstruct
    public void init() {
        Properties dbProperties = new Properties();
        try {
            File file = new File(fileAddress);
            if (file.exists()) {
                dbProperties.load(new FileInputStream(file));
                dbProperties.stringPropertyNames().forEach(System.out::println);
            } else {
                System.out.println("不存在");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
