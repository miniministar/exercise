package com.exercise.jdk8;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.function.IntBinaryOperator;

public class LambdaTest01 {

    public static void main(String[] args) throws Exception {
        Callable<Integer> integerCallable = () -> 5 ;
        Integer a = integerCallable.call();
        System.out.println(a);

        //类型声明
        IntBinaryOperator intBinaryOperator = (int a1, int a2) -> a1 + a2;

        //不用类型声明
        Comparator<Integer> tComparator = (num1, num2) -> num1 - num2;

        //大括号中的返回语句
        IntBinaryOperator intBinaryOperator1 = (int n1, int m1) -> {
            return n1 * m1;
        };

        //没有大括号的返回

        Comparator<Integer> tComparator1 = (Integer n2, Integer m2) -> n2 / m2;

        System.out.println(intBinaryOperator.applyAsInt(1, 2));
        System.out.println(tComparator.compare(1, 2));
        System.out.println(intBinaryOperator1.applyAsInt(1, 2));
        System.out.println(tComparator1.compare(1, 2));


        LambdaTest01 test = new LambdaTest01();
        MathOperation addition = (int n3, int m3) -> n3 + m3;
        System.out.println(" 10 + 5 =" + test.operate(10, 5, addition));
        MathOperation subtraction = (n4, m4) -> n4 - m4;
        System.out.println(" 10 - 5 =" + test.operate(10, 5, subtraction));
        System.out.println(" 10 * 5 =" + test.operate(10, 5, (n5, m5) -> n5 * m5) );
        System.out.println(" 10 / 5 =" + test.operate(10, 5, (int n6, int m6) -> {return n6 / m6;}));

        String msg = "common variable";
        test.sayMsg(msg , (msg1)-> System.out.println(msg1));
        test.sayMsg(staticMsg, (msg2) -> System.out.println(msg2));
        test.sayMsg(test.privateMsg, (msg3)-> System.out.println(msg3));
        test.sayMsg(test.finalMsg, (msg4)-> System.out.println(msg4 ));
        //在lambda表达式中只能访问外层的局部变量，全局的常量，全局的静态变量
        test.sayMsg("hello lambda!", (msg5)-> System.out.println(finalStaticMsg + ", " + msg5));
        test.sayMsg("hello lambda!", (msg6) -> System.out.println(staticMsg + ", " + msg6));
        //在lambda表达式中访问外层的局部变量
        test.sayMsg("hello lambda", (msg7)-> System.out.println(msg + ", " + msg7));
    }

    private String privateMsg = "private variable";
    public static String staticMsg = "static variable";
    private final String finalMsg = "final variable";
    private final static String finalStaticMsg = "final static variable";

    interface MathOperation{
        int operate(int a, int b);
    }

    interface MessageOperation{
        void say(String msg);
    }

    private int operate(int a, int b, MathOperation mathperation) {
        return mathperation.operate(a, b);
    }

    private void sayMsg(String msg, MessageOperation operation) {
        operation.say(msg);
    }
}
