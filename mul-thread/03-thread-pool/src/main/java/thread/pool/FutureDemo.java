package thread.pool;

import java.util.concurrent.*;
public class FutureDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("execute:call  " + Thread.currentThread().getName());
        Thread.sleep(1000);
        return "Hello Call";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureDemo futureDemo=new FutureDemo();
        FutureTask futureTask=new FutureTask(futureDemo);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        Future future =executorService.submit(futureDemo);
        System.out.println(future.get()); //阻塞获取结果
    }
}
