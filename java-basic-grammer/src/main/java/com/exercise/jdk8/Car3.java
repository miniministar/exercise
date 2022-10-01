package com.exercise.jdk8;

public class Car3 implements Vehicle, FourWheeler {

    @Override
    public void print() {
        Vehicle.run();
        System.out.println("重新默认方法");
    }

    public static void main(String[] args) {
        Car3 car3 = new Car3();
        car3.print();

        Car2 car2 = new Car2();
        car2.print();

        Vehicle vehicle = new Car3();
        vehicle.print();
    }
}
