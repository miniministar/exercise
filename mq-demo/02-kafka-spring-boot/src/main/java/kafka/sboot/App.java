package kafka.sboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        Producer producer = context.getBean(Producer.class);
        for (int i = 0; i < 10; i++) {
            producer.send();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
