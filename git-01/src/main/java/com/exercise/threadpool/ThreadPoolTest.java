package com.exercise.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable r = new Station();
        executorService.submit(r);
        executorService.submit(r);
        executorService.submit(r);
    }

}
