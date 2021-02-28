package thread.pool;

import java.util.concurrent.ArrayBlockingQueue;

public class Demo {
    ArrayBlockingQueue ab = new ArrayBlockingQueue(10);

    public void init() { //ThreadB ->worker

        new Thread(() -> {
            while (true) {
                try {
                    ab.take(); //阻塞获取
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    ab.take(); //阻塞获取
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    ab.take(); //阻塞获取
                    //task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void sendData() { //ThreadA
        ab.add("task");
    }
}
