package thread.juc.communicate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(); //重入锁
        Condition condition = lock.newCondition();
        //step 1
        new Thread(new ConditionWait(lock, condition)).start();// 阻塞await
        //step 2
        //(condtion为空)
        new Thread(new ConditionNotify(lock, condition)).start();//
    }
}
