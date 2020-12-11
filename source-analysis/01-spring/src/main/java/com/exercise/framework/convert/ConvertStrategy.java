package com.exercise.framework.convert;

import java.util.ArrayList;
import java.util.List;

public class ConvertStrategy{
    public static List<Converter> converters = new ArrayList<>();
    static {
        converters.add(new IntegerCovert());
        converters.add(new DoubleCovert());
    }

    public static Object convert(Class<?> clazz, String value) {
        for (Converter converter : converters) {
            if(converter.matches(clazz)) return converter.convert(value);
        }
        return value;
    }


}
