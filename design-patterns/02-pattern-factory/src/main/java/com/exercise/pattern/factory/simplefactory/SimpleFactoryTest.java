package com.exercise.pattern.factory.simplefactory;

import com.exercise.pattern.factory.ICourse;
import com.exercise.pattern.factory.JavaCourse;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        ICourse javaCourse = CourseFactory.create(JavaCourse.class);
        javaCourse.record();
    }
}
