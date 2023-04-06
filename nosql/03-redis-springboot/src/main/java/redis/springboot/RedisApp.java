package redis.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import redis.springboot.redisson.IdGenerator;

@RestController
@SpringBootApplication
public class RedisApp {

	public static void main(String[] args) {
		SpringApplication.run(RedisApp.class, args);
	}


	public String genId() {
		return IdGenerator.genId("CS");
	}
}
