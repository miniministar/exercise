package com.exercise.threadpool;

public class Customer extends Thread{
    private KFC kfc;

    public Customer(String name, KFC kfc) {
        super(name);
        this.kfc = kfc;
    }

    public Customer(KFC kfc) {
        this.kfc = kfc;
    }

    public KFC getKfc() {
        return kfc;
    }

    public void setKfc(KFC kfc) {
        this.kfc = kfc;
    }

    public void run() {
        int size = (int) (Math.random() * 5);
        while (true) {
            kfc.consume(size);
        }
    }
}
