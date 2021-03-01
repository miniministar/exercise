package com.exercise.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2019, 9, 15);
        LocalTime localTime = LocalTime.of(14, 14, 14);
        // 创建 LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        LocalDateTime localDateTime4 = localTime.atDate(localDate);
        System.out.println(
                  "localDateTime=" + localDateTime  + "\n"
                + "localDateTime1=" + localDateTime1  + "\n"
                + "localDateTime2=" + localDateTime2  + "\n"
                + "localDateTime3=" + localDateTime3  + "\n"
                + "localDateTime4=" + localDateTime4  + "\n"
        );
        // 获取LocalDate
        LocalDate localDate2 = localDateTime.toLocalDate();
        // 获取LocalTime
        LocalTime localTime2 = localDateTime.toLocalTime();
        System.out.println(
                "localDate=" + localDate  + "\n"
                + "localTime=" + localTime  + "\n"
                + "localDate2=" + localDate2  + "\n"
                + "localTime2=" + localTime2  + "\n"
                + "now=" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))  + "\n"
                + "now=" + localDate2  + " " + localTime2
        );
    }
}
