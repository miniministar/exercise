package com.exercise.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) throws Exception {
//        testCachedThreadPool();
//        testFixedThreadPool();
//        testScheduledThreadPool();
        testSingleThreadExecutor();
    }

    private static void testSingleThreadExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "------------" + index);
                }
            });
        }

    }

    private static void testScheduledThreadPool() {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
        System.out.println(Thread.currentThread().getName() + "定时任务开始，3秒后会有十个任务提交");
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "------------" + index);
                }
            }, 5, TimeUnit.SECONDS);
        }
//             每次延迟多少时间，周期执行
//            threadPool.scheduleWithFixedDelay();
//             延迟多少时间后，周期执行
//            threadPool.scheduleAtFixedRate();
    }

    private static void testFixedThreadPool() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            final  int index = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "-----------" + index);
                }
            });
        }


    }

    private static void testCachedThreadPool() throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            Thread.sleep(index * 1000);
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "---------"  + index);
                }
            });
        }
    }
}
