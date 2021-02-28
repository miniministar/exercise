package thread.juc.queue;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    static int count;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    //序号. -> 序号重叠.
    static void incr() {
        int i = atomicInteger.incrementAndGet();
//        System.out.println(i);
//        count++;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicTest::incr).start();
        }
        System.out.println("main" + atomicInteger.get());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("main" + atomicInteger.get());
    }

}
