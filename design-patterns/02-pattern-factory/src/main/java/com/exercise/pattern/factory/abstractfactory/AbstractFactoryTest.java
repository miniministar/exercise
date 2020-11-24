package com.exercise.pattern.factory.abstractfactory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        JavaCourseFactory factory = new JavaCourseFactory();
        INote note = factory.createNote();
        note.edit();
        IVideo video = factory.createVideo();
        video.record();
    }
}
