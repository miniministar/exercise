package com.exercise.thread;

import java.util.concurrent.*;

public class CreateThread {
    public static void main(String[] args) throws Exception {
        testExtendsThread();
        testImplementsRunnable();
        testImplementsCallable();
    }

    private static void testImplementsCallable() throws Exception {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<?> future = threadPool.submit(new ImplementsCallableDemo());
        System.out.println("" + future.get());
        threadPool.shutdown();
    }

    private static void testImplementsRunnable() {
        new Thread(new ImplementsRunnableDemo()).start();
    }

    private static void testExtendsThread() {
        new ExtendsThreadDemo().start();
    }

    static class ExtendsThreadDemo extends Thread {
        @Override
        public void run() {
            System.out.println("继承Thread类，重写run方法");
            System.out.println(Thread.currentThread().getName() );
        }
    }

    static class ImplementsRunnableDemo implements Runnable {
        @Override
        public void run() {
            System.out.println("实现Runnable接口，实现run方法");
            System.out.println(Thread.currentThread().getName() );
        }
    }

    private static class ImplementsCallableDemo implements Callable {
        @Override
        public Object call() throws Exception {
            System.out.println("实现Callable接口，实现call方法");
            System.out.println(Thread.currentThread().getName() );
            return "Callable";
        }
    }
}
