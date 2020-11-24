package com.exercise.pattern.factory;

public class PhpCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("php course recording");
    }
}
