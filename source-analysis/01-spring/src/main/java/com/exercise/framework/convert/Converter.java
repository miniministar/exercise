package com.exercise.framework.convert;

public abstract class Converter<T, S> {
    public abstract T convert(S source);

    public abstract Class<?>[] getCanConverterClass();

    public boolean matches(Class<?> clazz) {
        for (Class<?> aClass : getCanConverterClass()) {
            if(aClass == clazz) return true;
        }
        return false;
    }
}
