package com.exercise.jdk8;

public interface Vehicle {
    default void print() {
        System.out.println("默认方法，接口中可以有实现的方法");
    }

    static void run() {
        System.out.println("汽车启动");
    }
}
