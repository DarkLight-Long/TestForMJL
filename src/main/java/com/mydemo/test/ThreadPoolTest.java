package com.mydemo.test;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 未完成
 */
public class ThreadPoolTest {

    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,10,1000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));

    /**
     * thread不建议监控thread摧毁
     */
    @Test
    public void testForThread() {
        Thread thread = new Thread("test") {
            @Override
            public void run() {
                System.out.println("run");
            }

            @Override
            public void start() {
                System.out.println("start");
                super.start();
            }
        };
        thread.start();
    }

}
