package thread.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDemo {
    static Map<String, Object> cacheMap = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock read = rwl.readLock();
    static Lock write = rwl.writeLock();

    public static final Object get(String key) {
        System.out.println(Thread.currentThread().getName() + "开始读取数据");
        read.lock();//读锁
        try {
            return cacheMap.get(key);
        } finally {
            read.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        write.lock();//写锁
        System.out.println(Thread.currentThread().getName() + "开始写数据");
        try {
            return cacheMap.put(key, value);
        } finally {
            write.unlock();
        }
    }

    public static void main(String[] args) throws Exception {

        for (int i = 1; i < 11; i++) {
            new Thread(() -> {
                put("a", "a");
            }, "write thread " + i).start();
        }
//        TimeUnit.SECONDS.sleep(5);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                get("a");
            }, "read thread " + (i + 1)).start();
        }
    }

}