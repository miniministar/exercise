package com.exercise.dip;

public class TestDependenceInversion {
    public static void main(String[] args) {
        Student student = new Student();
        student.studyEnglish();
        student.studyMath();

        //依赖倒置
        DipStudent dipStudent = new DipStudent("zhangsan");
        dipStudent.study(new MathCourse());
        dipStudent.study(new EnglishCourse());
    }
}
