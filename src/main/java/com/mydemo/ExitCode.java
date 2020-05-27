package com.mydemo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
//都可以

//@Component
//public class ExitCode implements DisposableBean {
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("项目退出成功");
//    }

@Component
public class ExitCode {
        @PreDestroy
    public void destroy() throws Exception {
        System.out.println("项目退出成功");
    }
}