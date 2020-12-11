package com.exercise.framework.convert;

public class DoubleCovert extends Converter<Double, Object> {
    private Class<?> clazz[] = {Double.class, double.class};
    @Override
    public Double convert(Object source) {
        return Double.valueOf(source.toString());
    }

    @Override
    public Class<?>[] getCanConverterClass() {
        return this.clazz;
    }
}
