package com.exercise.jdk8;

import java.util.Arrays;
import java.util.List;

public class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        Car car = Car.create(Car::new);
        Car car2 = Car.create(Car::new);
        List<Car> carList = Arrays.asList(car, car2);
        carList.forEach(Car::collide);
        carList.forEach(Car::repair);

        Car car3 = Car.create(Car::new);
        carList.forEach(car3::follow);

        List<String> names = Arrays.asList("Google", "Taobao", "Tentent", "Baidu");
        names.forEach(System.out ::println);
    }
}
