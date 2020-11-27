package pattern.singleton.threadlocal;

public class ThreadLocalSingletonTest {
    public static void main(String[] args) {
//        System.out.println(ThreadLocalSingleton.getInstance());
//        System.out.println(ThreadLocalSingleton.getInstance());
//        System.out.println(ThreadLocalSingleton.getInstance());
        Thread t1 = new Thread(new ExecutorThread());
        t1.run();
        Thread t2 = new Thread(new ExecutorThread());
        t2.run();
        Thread t3 = new Thread(new ExecutorThread());
        t3.run();
    }
}