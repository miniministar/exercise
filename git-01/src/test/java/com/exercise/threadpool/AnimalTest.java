package com.exercise.threadpool;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void test() {
        Tortoise tortoise = new Tortoise();
        Rabbit rabbit = new Rabbit();
        LetOneStop letOneStop = new LetOneStop(tortoise);
        rabbit.calltoBack = letOneStop;
        LetOneStop letOneStop1 = new LetOneStop(rabbit);
        tortoise.calltoBack = letOneStop1;
        tortoise.start();
        rabbit.start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}