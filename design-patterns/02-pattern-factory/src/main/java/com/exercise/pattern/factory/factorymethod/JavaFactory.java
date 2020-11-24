package com.exercise.pattern.factory.factorymethod;

import com.exercise.pattern.factory.ICourse;
import com.exercise.pattern.factory.JavaCourse;

public class JavaFactory implements ICourseFactory {

    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
