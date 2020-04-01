package com.exercise.threadpool;

public class Rabbit extends Animal {

    public Rabbit() {
        setName("兔子");
    }

    public void running() {
        double dis = 0.5;
        length -= dis;
        if(length <= 0) {
            length = 0;
            System.out.println("兔子活动胜利");
            if(calltoBack != null) {
                calltoBack.win();
            }
        }
        System.out.println("兔子跑了" + dis + "米，距离终点还有" + length + "米");
        if(length % 2 == 0) {
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
