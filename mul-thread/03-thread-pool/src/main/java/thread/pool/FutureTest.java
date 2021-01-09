package thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest implements Callable<String> {
    @Override
    public String call() throws Exception {

        System.out.println("execute call");
        return "call";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTest futureTest = new FutureTest();
        FutureTask futureTask = new FutureTask(futureTest);
        new Thread(futureTask).start();

        System.out.println(1);
        System.out.println(futureTask.get());
        System.out.println(2);
    }
}
