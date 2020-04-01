package com.exercise.threadpool;

public class Tortoise extends Animal {

    public Tortoise() {
        setName("乌龟");
    }

    public void running() {
        double dis = 0.3;
        length -= dis;
        if(length <= 0) {
            length = 0;
            System.out.println("乌龟活动胜利");
            if(calltoBack != null) {
                calltoBack.win();
            }
        }
        System.out.println("乌龟跑了" + dis + "米，距离终点还有" + length + "米");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
