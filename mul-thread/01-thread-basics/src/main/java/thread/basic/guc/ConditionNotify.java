package thread.basic.guc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionNotify implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("begin - condition notify");
            condition.signal();
            System.out.println("end - condition notify");
        }finally {
            lock.unlock();
        }
    }
}
