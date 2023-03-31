package com.mydemo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 随机数测试 + 时间测试
 */
public class TestTime {

    public static void main(String[] args) throws ParseException, InterruptedException {
        int a = new Random().nextInt(10);
        System.out.println(a);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        long startTime = simpleDateFormat.parse("2021-09-31").getTime();
//        long now = new Date().getTime();
//        System.out.println(startTime);
//        if (startTime - now < 7L * 24 * 3600) {
//            System.out.println(2222);
//        } else {
//            System.out.println(23333);
//        }
        System.out.println(new Date().getTime());
        Thread.sleep(3000);
        System.out.println(new Date().getTime());

    }

}
