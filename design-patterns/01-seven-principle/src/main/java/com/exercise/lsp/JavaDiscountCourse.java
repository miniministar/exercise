package com.exercise.lsp;

import com.exercise.ocp.JavaCourse;

public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    // 根据里氏交互原则，子类不应该改变父类原有的功能，只可以扩展父类的功能
    public Double getDiscountPrice() {
        return super.getPrice() * 0.61;
    }

}
