package com.exercise.jdk8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 员工
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    // 姓
    private String name;
    // 城市
    private String city;
    // 销售额
    private Integer sales;

    //-------------------生成测试数据---------------------
    public static List<Employee> getEmps(){
        List<Employee> list = new ArrayList<>();
        Random rd = new Random();
        String[] citys = {"北京","上海","广州","杭州","深圳"};
        String[] firstName = {"张","李","杨","宁","刘","王","高","葛"};
        Integer[] sales = {100,50,30,20};

        for (int i = 0; i < 10; i++) {
            String city = citys[rd.nextInt(5)];
            Integer sale = sales[rd.nextInt(4)];
            String fname = firstName[rd.nextInt(firstName.length)];
            list.add(new Employee(fname,city,sale));
        }
        return list;
    }

}
