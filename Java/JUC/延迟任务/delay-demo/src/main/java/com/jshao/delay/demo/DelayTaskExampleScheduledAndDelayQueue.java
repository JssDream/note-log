package com.jshao.delay.demo;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.*;

/**
 * 2. Java API 提供了两种实现延迟任务的方法：DelayQueue 和 ScheduledExecutorService
 */
@Slf4j
public class DelayTaskExampleScheduledAndDelayQueue {
    public static void main(String[] args) {

//        log.info("Scheduled--->程序启动时间：{}", LocalDateTime.now());
//        scheduledExecutorServiceTask();

        /**===========DelayQueue============**/

        DelayQueue delayQueue = new DelayQueue();
        // 添加延迟任务
        delayQueue.put(new DelayElement(1000));
        delayQueue.put(new DelayElement(3000));
        delayQueue.put(new DelayElement(5000));
        log.info("DelayQueue--->开始时间：{}" ,DateFormat.getDateTimeInstance().format(new Date()));
        while (!delayQueue.isEmpty()){
            // 执行延迟任务
            try {
                log.info("DelayQueue--->执行延迟任务: {}", delayQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("DelayQueue--->结束时间：{}", DateFormat.getDateTimeInstance().format(new Date()));
    }

    /**
     * ScheduledExecutorService 实现固定频率一直循环执行任务
     */
    public static void scheduledExecutorServiceTask() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(
                () -> {
                    // 执行任务的业务代码
                    log.info("Scheduled--->执行任务，执行时间：{}", LocalDateTime.now());
                },
                2, // 初次执行间隔
                2, // 2s 执行一次
                TimeUnit.SECONDS);
    }

    /**
     * DelayQueue 延迟队列
     *
     * DelayQueue 是一个支持延时获取元素的无界阻塞队列，队列中的元素必须实现 Delayed 接口，并重写 getDelay(TimeUnit) 和 compareTo(Delayed) 方法
     *
     * poll():获取并移除队列的超时元素，没有则返回空
     * take():获取并移除队列的超时元素，如果没有则wait当前线程，直到有元素满足超时条件，返回结果。
     *
     */
    static class DelayElement implements Delayed {
        // 延迟截止时间（单面：毫秒）
        long delayTime = System.currentTimeMillis();
        public DelayElement(long delayTime) {
            this.delayTime = (this.delayTime + delayTime);
        }
        // 获取剩余时间
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
        // 队列里元素的排序依据
        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else {
                return 0;
            }
        }
        @Override
        public String toString() {
            return DateFormat.getDateTimeInstance().format(new Date(delayTime));
        }
    }
}
