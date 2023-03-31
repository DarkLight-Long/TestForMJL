package com.mydemo.test.thred;

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
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,10,1000,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),
            new BasicThreadFactory.Builder().namingPattern("name%s").daemon(true).build());

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

    public static void main(String[] args) {
        ThreadFactory threadFactory = (Runnable r) -> {
            Thread thread = new Thread(r, "test");
            thread.setDaemon(true);
            // 1. 捕获线程执行中的异常 execute可以  submit有返回体，异常在返回体中
            // 2. 可以直接线程内try catch获取异常
            // 3. execute可以通过重些afterExecute方法,submit需要单独处理
            thread.setUncaughtExceptionHandler((Thread t,Throwable e) ->{
                System.out.println(t.getName() + ";Exception:" + e.getMessage());
            });
            return thread;
        };
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 2, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10) ,threadFactory) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
//                super.afterExecute(r, t);

                // submit的单独处理
                if (r instanceof FutureTask) {
                    Future<?> future = (Future<?>) r;
                    try {
                        future.get();// 此处就是异常
                    } catch (Exception e) {}
                }
            }
        };
        Future future = threadPool.submit(() -> {
            System.out.println(1);
            System.out.println(1/0);
        });
        try {
            System.out.println(23333333);

            // 打开会导致3秒延时也被延后执行 且结果都包含里面(包括execute的)
//            System.out.println(future.get());
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(333333333);
        threadPool.execute(() -> {
            System.out.println(2);
            System.out.println(1/0);
        });

    }

}
