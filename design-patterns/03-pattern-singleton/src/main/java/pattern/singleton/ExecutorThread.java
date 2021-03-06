package pattern.singleton;

public class ExecutorThread implements Runnable {
    @Override
    public void run() {
//        LazySingleton singleton = LazySingleton.getSingleton();
        InnerLazySingleton singleton = InnerLazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + singleton);
    }
}
