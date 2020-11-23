package com.exercise.dip;

public class EnglishCourse implements ICourse {
    private String name = "english";
    @Override
    public void study() {
        System.out.println("study english");
    }

    @Override
    public String getCourse() {
        return name;
    }
}
