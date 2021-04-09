package com.mydemo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DanliModelDelay {

    {
        System.out.println("init danliModelDelay");
    }

    private static class DanliInstance {
        {
            System.out.println("init getinstance");
        }
        private final static DanliModelDelay INSTANCE = new DanliModelDelay();
    }
    private DanliModelDelay() {};

    public static void test() {
        System.out.println("test");
    }

    public static DanliModelDelay getInstance() {
        return DanliInstance.INSTANCE;
    }

    private static final ExecutorService pool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        DanliModelDelay.test();
        System.out.println("************");
//        DanliModelDelay.getInstance();
        Thread a = new Thread(){
            @Override
            public void run() {
                System.out.println(DanliModelDelay.getInstance() + ":a");
            }
        };
        Thread b = new Thread() {
            @Override
            public void run() {
                System.out.println(DanliModelDelay.getInstance() + ":b");
            }
        };
        pool.execute(a);
        pool.execute(b);
        pool.shutdown();
    }

}
