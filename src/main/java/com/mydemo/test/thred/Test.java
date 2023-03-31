package com.mydemo.test.thred;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 线程相关测试和使用
 *  Note：
 *   1. 守护线程及垃圾回收线程(Java内存回收机制也是类似守护线程) => 主线程执行结束后，守护线程停止
 *      结果就是 => 当线程池中某个线程执行sleep时，可能会导致线程池提前关闭
 */
public class Test {

    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1,
            1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1, true),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("test-thread-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    static class ThreadTestR implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "进入Runnable");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadPool 当前taskCount:" + threadPool.getTaskCount());
            System.out.println("threadPool 当前queueSize:" + threadPool.getQueue().size());
            System.out.println(Thread.currentThread().getName() + "完成Runnable");
        }
    }

    static class ThreadTestC implements Callable {

        @Override
        public Object call() {
            System.out.println(Thread.currentThread().getName() + "进入Callable");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            System.out.println("threadPool 当前taskCount:" + threadPool.getTaskCount());
            System.out.println("threadPool 当前queueSize:" + threadPool.getQueue().size());
            System.out.println(Thread.currentThread().getName() + "完成Callable");
            return "ok";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Date());
        threadPool.execute(new ThreadTestR());

        Future future = threadPool.submit(new ThreadTestC());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println(new Date());
            System.out.println(Thread.activeCount());
            System.out.println(Thread.currentThread().getName() + "end");
            System.out.println("threadPool 当前activeCount:" + threadPool.getActiveCount());
            System.out.println("threadPool 当前taskCount:" + threadPool.getTaskCount());
            System.out.println("threadPool 当前queueSize:" + threadPool.getQueue().size());
        }));
        try {
//            System.out.println(future.get().toString());
            threadPool.execute(new ThreadTestR());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        CompletableFuture completableFuture = CompletableFuture.runAsync(new ThreadTestR(), threadPool);
    }
}
