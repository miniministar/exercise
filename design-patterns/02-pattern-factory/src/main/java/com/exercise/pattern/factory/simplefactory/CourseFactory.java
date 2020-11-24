package com.exercise.pattern.factory.simplefactory;

import com.exercise.pattern.factory.ICourse;

public class CourseFactory {
    public static ICourse create(Class<? extends ICourse> clazz) {
        if(clazz != null) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
