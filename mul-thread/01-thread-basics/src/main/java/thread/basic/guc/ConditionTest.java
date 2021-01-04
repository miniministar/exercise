package thread.basic.guc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread1 = new Thread(new ConditionWait(lock, condition));
        Thread thread2 = new Thread(new ConditionNotify(lock, condition));
        thread1.start();
        thread2.start();
    }
}
