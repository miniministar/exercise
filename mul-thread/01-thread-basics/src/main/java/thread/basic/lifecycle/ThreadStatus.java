package thread.basic.lifecycle;

import java.util.concurrent.TimeUnit;

public class ThreadStatus {
    public static void main(String[] args) {
        //TIME_WAITING
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("当前线程：" + Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "timewaiting").start();

        //WAITING，线程在 ThreadStatus 类锁上通过wait 进行等待
        new Thread(() -> {
            while (true) {
                synchronized (ThreadStatus.class) {
                    try {
                        System.out.println("当前线程：" + Thread.currentThread().getName());
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Waiting").start();

        //线程在 ThreadStatus 加锁后，不会释放锁

        new Thread(new BlockedDemo(), "BlockDemo-01").start();
        new Thread(new BlockedDemo(), "BlockDemo-02").start();
    }

    static class BlockedDemo extends Thread {
        public void run() {
            synchronized (BlockedDemo.class) {

                while (true) {
                    try {
                        System.out.println("当前线程：" + Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        }

    }

}
