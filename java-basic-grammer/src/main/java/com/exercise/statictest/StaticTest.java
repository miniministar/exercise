package com.exercise.statictest;

public class StaticTest {
    private static String name = "woobo";
    private String num = "X001";

    static class Person { // 静态内部类可以用 public,protected,private  修饰
        // 静态内部类中可以定义静态或者非静态的成员
        private String address = "China";
        private static String x = "as";
        public String mail = "kongbowoo@yahoo.com.cn";//内部类公有成员

        public void display() {
            // 不能直接访问外部类的非静态成员
            // 静态内部类不能访问外部类的非静态成员(包括非静态变量和非静态方法)
            // System.out.println(num);
            //只能直接访问外部类的静态成员
            //静态内部类只能访问外部类的静态成员(包括静态变量和静态方法)
            System.out.println(name);
             System.out.println("Inner " +address);//访问本内部类成员。
        }
    }

    public void printInfo() {
        Person person = new Person();
        //外部类访问内部类的非静态成员:实例化内部类即可
        person.display();
        //System.out.println(mail);//不可访问
        //System.out.println(address);//不可访问
        System.out.println(person.address);//可以访问内部类的私有成员

        System.out.println(Person.x);// 外部类访问内部类的静态成员：内部类.静态成员
        System.out.println(person.mail);//可以访问内部类的公有成员
    }

    public static void main(String[] args) {
        StaticTest staticTest = new StaticTest();
        staticTest.printInfo();
    }
}