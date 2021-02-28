package thread.basic.jmm;

public class HappenBeforeSynchronized extends Thread{
    static int x = 10;

    @Override
    public void run() {
        synchronized (HappenBeforeSynchronized.class) {
            // 此处自动加锁
            // x 是共享变量, 初始值 =10
            System.out.println("线程："  +Thread.currentThread().getName() +", x = " +x);
            if (x < 12) {
                x = 12;
            }
        }
        // 此处自动解锁
    }
    public static void main(String[] args) throws Exception {
       new HappenBeforeSynchronized().start();
       new HappenBeforeSynchronized().start();
        System.out.println(x);
    }
}

