package thread.juc.queue;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingDemo {

    ArrayBlockingQueue<String> ab=new ArrayBlockingQueue(10);//FIFO的队列

    public void init(){
        Iterator<String> it=ab.iterator();
        new Thread(()->{
            while(true) {
                try {
                    String data = ab.take(); //阻塞方式获得元素
                    System.out.println("receive:" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void addData(String data) throws InterruptedException {
        ab.add(data);
        System.out.println("sendData:"+data);
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingDemo blockingDemo=new BlockingDemo();
        blockingDemo.init();
        for(int i=0;i<20;i++){
            blockingDemo.addData("data:"+i);
        }
    }
}
