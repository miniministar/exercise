package com.exercise.threadpool;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class BankTest {

    private Bank bank;
    @Before
    public void setUp() throws Exception {
        bank = new Bank();
    }

    @Test
    public void counter() {
        System.out.println("银行有" + bank.money);
        PersonA a = new PersonA("A", bank);
        PersonB b = new PersonB("B", bank);
        a.start();
        b.start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}