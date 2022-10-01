package com.exercise.jdk8;

public class Car2 implements Vehicle, FourWheeler {
    @Override
    public void print() {
        Vehicle.super.print();
    }
}
