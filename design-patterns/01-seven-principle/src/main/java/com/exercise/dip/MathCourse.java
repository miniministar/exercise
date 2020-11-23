package com.exercise.dip;

public class MathCourse implements ICourse {
    private String name = "math";
    @Override
    public void study() {
        System.out.println("study math");
    }

    @Override
    public String getCourse() {
        return name;
    }
}
