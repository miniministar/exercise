package com.exercise.date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class InstantTest {
    public static void main(String[] args) {
        //Instant 获取秒数，用于表示一个时间戳（精确到纳秒）
        // 创建Instant对象
        Instant instant = Instant.now();
        // 获取秒数
        long currentSecond = instant.getEpochSecond();
        // 获取毫秒数
        long currentMilli = instant.toEpochMilli();

        System.out.println("currentSecond=" + currentSecond);
        System.out.println("currentMilli=" + currentMilli);

        // Duration.between()方法创建 Duration 对象
        LocalDateTime from = LocalDateTime.of(2017, Month.JANUARY, 1, 00, 0, 0);    // 2017-01-01 00:00:00
        LocalDateTime to = LocalDateTime.of(2019, Month.SEPTEMBER, 12, 14, 28, 0);     // 2019-09-15 14:28:00
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Duration duration = Duration.between(from, to);     // 表示从 from 到 to 这段时间
        long days = duration.toDays();              // 这段时间的总天数
        long hours = duration.toHours();            // 这段时间的小时数
        long minutes = duration.toMinutes();        // 这段时间的分钟数
        long seconds = duration.getSeconds();       // 这段时间的秒数
        long milliSeconds = duration.toMillis();    // 这段时间的毫秒数
        long nanoSeconds = duration.toNanos();      // 这段时间的纳秒数
        System.out.println("form=" + from.format(df) +",to=" + to.format(df));
        System.out.println("days=" + days );
        System.out.println("hours=" + hours );
        System.out.println("minutes=" + minutes );
        System.out.println("seconds=" + seconds );
        System.out.println("milliSeconds=" + milliSeconds );
        System.out.println("nanoSeconds=" + nanoSeconds );
    }
}
