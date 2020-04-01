package com.exercise.threadpool;

import java.util.ArrayList;
import java.util.List;

public class KFC {

    private String[] names = {"薯条", "烧饭", "鸡翅", "可乐"};

    static final int MAX = 20;
    private List foods = new ArrayList();

    public void prod(int index) {
        synchronized (this) {
            while (foods.size() > MAX) {
                System.out.println("食材够了");
                this.notifyAll();
                try {
                    this.wait();
                    System.out.println( Thread.currentThread().getName() + "停止生产");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + "开始生产食物" );
            for (int i = 0; i < index; i ++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Food food = new Food(names[(int)(Math.random()* 4)]);
                foods.add(food);
                System.out.println( Thread.currentThread().getName()+ "生产了" + food.getName() + "，现在有食物："+foods.size() + "个");
            }
        }

    }

    public void consume(int index) {
        synchronized (this) {
            while (foods.size() < index) {
                System.out.println("食材不够了");
                this.notifyAll();
                try {
                    this.wait();
                    System.out.println(Thread.currentThread().getName() + "停止消费" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("开始消费");
            for(int i = 0 ; i < index; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Food food = (Food) foods.remove(foods.size() -1);
                System.out.println( Thread.currentThread().getName()+"消费了一个" + food.getName() + "，还剩" + foods.size()+" 个");
            }

        }
    }

}
