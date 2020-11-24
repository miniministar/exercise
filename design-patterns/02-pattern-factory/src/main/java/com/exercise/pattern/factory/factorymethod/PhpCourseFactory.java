package com.exercise.pattern.factory.factorymethod;

import com.exercise.pattern.factory.ICourse;
import com.exercise.pattern.factory.PhpCourse;

public class PhpCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new PhpCourse();
    }
}
