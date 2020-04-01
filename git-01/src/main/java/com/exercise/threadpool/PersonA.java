package com.exercise.threadpool;

public class PersonA extends Thread {
    Bank bank;

    public PersonA(String name, Bank bank) {
        super(name);
        this.bank = bank;
    }

    public PersonA() {
    }

    @Override
    public void run() {
        while (Bank.money >= 100) {
            bank.counter(Thread.currentThread().getName(), 10);
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
