package com.exercise.threadpool;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class KFCTest {

    @Test
    public void consuming() {
        KFC kfc = new KFC();

        Customer c1 = new Customer("消费者1", kfc);
        Customer c2 = new Customer("消费者2", kfc);
        Customer c3 = new Customer("消费者3", kfc);
        Customer c4 = new Customer("消费者4", kfc);
        Customer c5 = new Customer("消费者5", kfc);

        Waiter w1 = new Waiter("生产者1", kfc);
        Waiter w2 = new Waiter("生产者2", kfc);
        Waiter w3 = new Waiter("生产者3", kfc);

        w1.start();
        w2.start();
        w3.start();

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}