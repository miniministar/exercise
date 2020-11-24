package com.exercise.pattern.factory.abstractfactory;

public class JavaCourseFactory implements  ICourseFactory{
    @Override
    public INote createNote() {
        return new JavaNote();
    }

    @Override
    public IVideo createVideo() {
        return new JavaVideo();
    }
}
