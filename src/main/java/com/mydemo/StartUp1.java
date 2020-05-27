package com.mydemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class StartUp1 implements CommandLineRunner {

    @Override
    public void run (String... strings) throws Exception {
        System.out.println("启动启动进程1");
    }
}
