package com.exercise.lop;

import java.util.ArrayList;
import java.util.List;

public class TeamLeader {
    public void checkNumberOfCourse() {
        List<Course> list = new ArrayList<>();
        for (int i = 0 ; i < 5; i++) {
            list.add(new Course());
        }
        System.out.println("课程数量为：" + list.size());
    }
}
