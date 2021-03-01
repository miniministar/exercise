package com.exercise.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

public class LocalDateTest {
    public static void main(String[] args) {
        // 创建 LocalDate
        // 获取当前年月日
//        LocalDate localDate = LocalDate.now();
        // 构造指定的年月日
        LocalDate localDate = LocalDate.of(2019, 9, 15);
        // 获取年、月、日、星期几
        int year = localDate.getYear();
        int year1 = localDate.get(ChronoField.YEAR);
        System.out.println("year=" + year + ", year1=" + year1);
        Month month = localDate.getMonth();
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("mouth=" + month +", mouth int = " + month1);
        int day = localDate.getDayOfMonth();
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println("day of month " + day + ", day1 = " + day1);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);
        System.out.println("day of week " + dayOfWeek +", day1 of week " +dayOfWeek1);
    }
}
