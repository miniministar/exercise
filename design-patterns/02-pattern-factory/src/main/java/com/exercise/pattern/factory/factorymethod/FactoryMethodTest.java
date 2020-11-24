package com.exercise.pattern.factory.factorymethod;

import com.exercise.pattern.factory.ICourse;

public class FactoryMethodTest {
    public static void main(String[] args) {
        ICourse java = new JavaFactory().create();
        java.record();

        ICourse php = new PhpCourseFactory().create();
        php.record();
    }
}
