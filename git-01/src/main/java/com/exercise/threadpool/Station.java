package com.exercise.threadpool;

public class Station implements Runnable {

    static int tickets = 100;

    public void run() {
        while (tickets > 0) {
            synchronized (this) {
                if(tickets > 0) {
                    System.out.println(Thread.currentThread().getName()+"卖出了第：" + tickets +"张票");
                    tickets --;
                }else {
                    System.out.println("票买完了");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
