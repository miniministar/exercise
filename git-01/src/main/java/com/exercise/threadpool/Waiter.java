package com.exercise.threadpool;

public class Waiter extends Thread{
    private KFC kfc;


    public Waiter(String name, KFC kfc) {
        super(name);
        this.kfc = kfc;
    }

    public Waiter(KFC kfc) {
        this.kfc = kfc;
    }

    public KFC getKfc() {
        return kfc;
    }

    public void setKfc(KFC kfc) {
        this.kfc = kfc;
    }

    public void run() {
        int size = (int) (Math.random() * 5) + 5;
        while (true) {
            kfc.prod(size);
        }
    }
}
