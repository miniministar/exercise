package com.exercise.dip;

public class DipStudent {
    private String name;

    public DipStudent() {
    }

    public DipStudent(String name) {
        this.name = name;
    }

    public void study(ICourse course) {
        System.out.println(this.name + " study " + course.getCourse());
    }
}
