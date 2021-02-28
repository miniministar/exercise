package thread.juc.communicate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo extends Thread {
    static CountDownLatch countDownLatch = new CountDownLatch(3);
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new CountDownLatchDemo().start();
        }
        System.out.println("main1");
        countDownLatch.await();
        System.out.println("main2");
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println("ThreadName:" + Thread.currentThread().getName());
    }
}
