package thread.basic.create;

public class VolatileTest {
    public static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            int i =0;
            while (!stop) {
                i++;
            }
        });

        thread.start();
        Thread.sleep(1000);
        stop = true;
    }
}