package redis.redission.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonAppTests {

    @Autowired
    private IdGenerator generator;
    private static final ConcurrentHashMap map = new ConcurrentHashMap();
    static int num = 100;
    static CountDownLatch countDownLatch = new CountDownLatch(num);

	@Test
	public void testGen() throws InterruptedException {
	    for (int i = 0; i < num; i++) {
            new Thread(()->{
                String id = generator.gen("CS");
                System.out.println(id);
            }).start();
        }
	}

}
