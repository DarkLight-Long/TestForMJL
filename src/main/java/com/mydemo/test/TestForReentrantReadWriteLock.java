package com.mydemo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *  - 读锁： 其他线程只允许读，不允许写
 *  - 写锁： 其他线程不允许读，不允许写
 *
 *  读写锁转换
 *   只允许写锁转为读锁。即 先获取写锁，在获取读锁，释放写锁后，保留读锁
 */
public class TestForReentrantReadWriteLock {
    private final static ExecutorService result = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        result.execute(new Thread() {
            @Override
            public void run() {
                Props props = Props.getInstance();
                System.out.println(props);
                try {
                    props.readLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        });

        result.execute(() -> {
            Props props = Props.getInstance();
            System.out.println(props);
            try {
                props.readLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        });

        countDownLatch.await();

        Future<String> futureA = result.submit(()->{
            Props props = Props.getInstance();
            System.out.println(props);
            try {
                props.writeLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "callableA";
        });

        Future<String> futureB = result.submit(()->{
            Props props = Props.getInstance();
            System.out.println(props);
            try {
                props.writeLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "callableB";
        });

        try {
            System.out.println(futureA.get());
            System.out.println(futureB.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        result.shutdown();
    }

    static class Props {
        private final static Logger logger = LoggerFactory.getLogger(Props.class);
        private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private static Props props;

        private Props() { }

        public static Props getInstance() {
            if (null == props) {
                lock.writeLock().lock();
                logger.info("获取写锁" + Thread.currentThread().getName());
                if (null == props) {
                    props = new Props();
                    System.out.println(props);
                    logger.info("获取真实写锁" + Thread.currentThread().getName());
                } else {
                    logger.info("未真实获取写锁" + Thread.currentThread().getName());
                }
                logger.info("释放写锁" + Thread.currentThread().getName());
                lock.writeLock().unlock();
            }

            return props;
        }

        public void readLock() throws InterruptedException {
            lock.readLock().lock();
            logger.info("获取读锁" + Thread.currentThread().getName());
            Thread.sleep(1000);
            logger.info("读取" + Thread.currentThread().getName());
            logger.info("释放读锁" + Thread.currentThread().getName());
            lock.readLock().unlock();
        }

        public void writeLock() throws InterruptedException {
            lock.writeLock().lock();
            logger.info("获取写锁" + Thread.currentThread().getName());
            Thread.sleep(1000);
            logger.info("写入" + Thread.currentThread().getName());
            logger.info("释放写锁" + Thread.currentThread().getName());
            lock.writeLock().unlock();
        }
    }

}
