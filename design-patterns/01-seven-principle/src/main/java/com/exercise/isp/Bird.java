package com.exercise.isp;

public class Bird implements IAnimal , IFlyAnimal {
    @Override
    public void eat() {
        System.out.println("bird eat something");
    }

    @Override
    public void fly() {
        System.out.println("bird can fly");
    }
}
