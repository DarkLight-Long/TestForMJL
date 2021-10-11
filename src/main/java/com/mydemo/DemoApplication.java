package com.mydemo;

import com.mydemo.demo.config.DataBaseTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  若依启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");


        // DataBaseTest.class 只用来获取ioc容器的媒介
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataBaseTest.class);

        String[] beanNames = applicationContext.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames));
    }

}
