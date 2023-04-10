package redis.redission;

import com.pig4cloud.plugin.idempotent.annotation.Idempotent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.redission.spring.IdGenerator;
import redis.redission.spring.RepeatSubmitService;

@RestController
@SpringBootApplication
public class RedisApp {

	public static void main(String[] args) {
		SpringApplication.run(RedisApp.class, args);
	}

	@Autowired
	private IdGenerator idGenerator;

	@Autowired
	private RepeatSubmitService repeatSubmitService;

	@GetMapping("/genId")
	public String getId() {
		return idGenerator.gen("CS");
	}

	@Idempotent(key = "'user:add:'.concat(#username)", expireTime = 10)
	@GetMapping("/add/{username}")
	public String repeatSubmit(@PathVariable String username) {
		return repeatSubmitService.add();
	}

	@GetMapping("/add2/{username}")
	public String repeatSubmit2(@PathVariable String username) {
		repeatSubmitService.repeatCheck(username);
		return "success";
	}
}
