package thread.basic.jmm;

public class HappenBeforeStart {
    static int x = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
        // 主线程调用 t1.start() 之前
        // 所有对共享变量的修改，此处皆可见
        // 此例中，x==10
            System.out.println(x);
        });
        // 此处对共享变量 x 修改
        x = 10;
        // 主线程启动子线程
        t1.start();
    }
}

