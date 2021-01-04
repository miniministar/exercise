package com.exercise.framework.convert;

public class IntegerCovert extends Converter<Integer, Object> {
    private Class<?> clazz[] = {Integer.class, int.class};
    @Override
    public Integer convert(Object source) {
        return Integer.valueOf(source.toString());
    }

    @Override
    public Class<?>[] getCanConverterClass() {
        return this.clazz;
    }
}
