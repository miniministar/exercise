package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {
    static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println(111);
        });
        executorService.submit(thread1);
        executorService.shutdown();
    }
}
