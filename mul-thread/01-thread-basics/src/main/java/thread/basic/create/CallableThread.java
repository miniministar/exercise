package thread.basic.create;

import java.util.concurrent.*;

public class CallableThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        int a = 1;
        int b = 2;
        System.out.println(a+b);
        return "执行结果为：" + (a+b);
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CallableThread callableThread = new CallableThread();
        Future<String> future = executorService.submit(callableThread);
        System.out.println("提交了任务");
        System.out.println(future.get());
        System.out.println("提交了任务2");
        executorService.shutdown();
    }
}
