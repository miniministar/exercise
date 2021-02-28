package thread.basic.lifecycle;

import java.util.concurrent.TimeUnit;

public class Interrupt3Demo {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Num:" + i);
        }, "interruptDe mo");

        thread.start();

        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        TimeUnit.SECONDS.sleep(10);

    }
}
