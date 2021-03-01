package com.exercise.date;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class LocalTimeTest {
    public static void main(String[] args) {
        // 创建 LocalTime
        LocalTime localTime = LocalTime.of(14, 14, 14);
        LocalTime localTime1 = LocalTime.now();
        // 获取小时
        int hour = localTime.getHour();
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
        System.out.println("hour = " + hour + ", hour1=" + hour1);
        // 获取分
        int minute = localTime.getMinute();
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        System.out.println("minute = " + minute + ", minute1=" + minute1);
        // 获取秒
        int second = localTime.getMinute();
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println("second = " + second + ", second1=" + second1);
    }
}
