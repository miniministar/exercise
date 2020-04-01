package com.exercise.threadpool;

public class Bank {

    static int money  = 1000;

    public void counter(String name, int money) {
        Bank.money -= money;
        System.out.println(name + "取走了" + money +"， 还剩" + Bank.money);
    }

    public void ATM(String name, int money) {
        Bank.money -= money;
        System.out.println(name + "取走了" + money +", 还剩" + Bank.money);
    }

}
