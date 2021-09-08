package thread.basic.future;

import java.util.Random;
import java.util.concurrent.*;

public class FutureDemo {
   static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Integer> future = calculate(10);
        Future<Integer> future2 = calculate(12);
        boolean done = false;
        while (!done) {
            if(future.isDone()) {
                System.out.println(future.get());
            }
            if(future2.isDone()) {
                System.out.println(future2.get());
            }
            if(future.isDone() && future2.isDone()) {
                done = true;
                break;
            }
            TimeUnit.MILLISECONDS.sleep(10);
        }

    }

    private static Future<Integer> calculate(int input) {
        Future<Integer> result = pool.submit(() -> {
            System.out.println("Calculating..." + input);
            Random random = new Random();
            int i = 100 * (random.nextInt(10)  + 1);
            System.out.println("Calculating..." + input  + "...." + i );
            Thread.sleep( i );
            return input * input;
        });
        return result;
    }


}
