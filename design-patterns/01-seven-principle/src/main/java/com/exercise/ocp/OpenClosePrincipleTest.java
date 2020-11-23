package com.exercise.ocp;

public class OpenClosePrincipleTest {
    public static void main(String[] args) {
        JavaCourse javaCourse = new JavaCourse(1, "java", 300.0);
        JavaDiscountCourse javaDiscountCourse = new JavaDiscountCourse(1, "java", 300.0);

        System.out.println("java course price:" + javaCourse.getPrice());
        System.out.println("java discount course price: " + javaDiscountCourse.getPrice());
    }
}
