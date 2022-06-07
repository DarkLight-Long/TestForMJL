package com.mydemo.test;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 未完成
 */
public class ThreadPoolTest {

    public static final ExecutorService executorService = Executors.newScheduledThreadPool(1,
            // 线程命名以及守护线程（守护线程能够在程序停止后自动关闭）
            new BasicThreadFactory.Builder().namingPattern("name%s").daemon(true).build());
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
