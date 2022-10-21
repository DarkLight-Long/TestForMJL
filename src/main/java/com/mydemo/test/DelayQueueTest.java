package com.mydemo.test;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.concurrent.*;
/**
 * 2021.11.2
 */
public class DelayQueueTest {

    public final static Logger logger = LoggerFactory.getLogger(DelayQueueTest.class);
    public final static DelayQueue<DelayTask> taskDelayQueue = new DelayQueue<>();
    public final static ExecutorService threadPool = Executors.newFixedThreadPool(1);
    public final static int queueMaxSize = 10;
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                addDelayTask();
            }
        };
        threadPool.execute(thread);
        while (true) {
            try {
                DelayTask delayTask = taskDelayQueue.poll(0, TimeUnit.MILLISECONDS);
                if (null == delayTask) {
                    Thread.sleep(2000);
                    throw new Exception("延迟对列为空");
                }
                System.out.println(delayTask.toString());
                Thread.sleep(1000);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public static void addDelayTask() {
        while (true) {
            if (taskDelayQueue.size() < queueMaxSize) {
                DelayTask delayTask = new DelayTask(new Random().nextInt(100), "DelayTask" + (char)(new Random().nextInt(26) + 65));
                taskDelayQueue.add(delayTask);
            }
            try {
                if (taskDelayQueue.size() == queueMaxSize) {
                    logger.warn("延迟队列已满，稍后继续添加");
                    Thread.sleep(8000);
                } else {
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Data
    public static class DelayTask implements Delayed {

        private long timeOutTime;
        private String taskName;

        DelayTask(long nanoTime, String taskName) {
            timeOutTime = System.nanoTime() + nanoTime;
            this.taskName = taskName;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(timeOutTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == this) {
                return 0;
            }
            DelayTask other = (DelayTask) o;
            long time = timeOutTime - other.getTimeOutTime();
            return time == 0 ? 0 : (time > 0) ? 1 : -1;
        }

        @Override
        public String toString() {
            return  "任务名称：" + this.taskName + ", 任务还有多久:" + getDelay(TimeUnit.NANOSECONDS);
        }
    }

}
