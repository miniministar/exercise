package com.exercise.jdk8;

public interface FourWheeler {
    default void print() {
        System.out.println("默认方法2");
    }
}
