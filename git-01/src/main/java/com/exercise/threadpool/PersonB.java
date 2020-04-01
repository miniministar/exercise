package com.exercise.threadpool;

public class PersonB extends Thread {
    Bank bank;

    public PersonB(String name, Bank bank) {
        super(name);
        this.bank = bank;
    }

    public PersonB() {
    }

    @Override
    public void run() {
        while (Bank.money >= 200) {
            bank.ATM(Thread.currentThread().getName(), 20);
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
