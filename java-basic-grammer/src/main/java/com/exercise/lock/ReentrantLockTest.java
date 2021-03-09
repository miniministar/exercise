package com.exercise.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private final static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread(()->{
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取了锁：i = " + index);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }).start();
        }
    }
}
