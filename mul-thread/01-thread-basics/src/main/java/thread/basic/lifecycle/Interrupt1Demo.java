package thread.basic.lifecycle;

import java.util.concurrent.TimeUnit;

public class Interrupt1Demo {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                //默认情况下isInterrupted 返回 false、通过thread.interrupt 变成了true
                i++;
                if(i%100 == 0)
                System.out.println("线程："+ Thread.currentThread().getName()+",i=" +i);
            }
            System.out.println("Num:" + i);
        }, "interruptDemo");

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //加和不加的效果
    }
}
