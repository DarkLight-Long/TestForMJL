package com.mydemo.test;

import java.util.concurrent.*;

/**
 * 未完成
 */
public class ThreadPoolTest {

    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,10,1000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));

    public static void main(String[] args) {

    }

}
