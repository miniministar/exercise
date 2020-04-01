package com.exercise.threadpool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StationTest {

    private Station station;

    @Before
    public void setUp() throws Exception {
        station = new Station();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(station);
        executorService.submit(station);
        executorService.submit(station);
        try {
            System.in.read();//加入该代码，让主线程不挂掉，junit测试时必须要加
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}