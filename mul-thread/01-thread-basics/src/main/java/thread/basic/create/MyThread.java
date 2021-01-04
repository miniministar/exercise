package thread.basic.create;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("当前线程名称：" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        System.out.println("main thread:" + Thread.currentThread().getName());
    }
}
