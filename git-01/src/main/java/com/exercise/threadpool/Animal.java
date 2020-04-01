package com.exercise.threadpool;

public abstract class Animal extends Thread {
    public  double length = 20;

    public abstract void  running();

    @Override
    public void run() {
        while (length > 0 )
            running();
    }

    public static interface CalltoBack {
        public void win();
    }

    public CalltoBack calltoBack;

}
