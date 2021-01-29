package shardjdbc.java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "shardjdbc.java.mapper")
public class ShardApp {
	public static void main(String[] args) {
		SpringApplication.run(ShardApp.class, args);
	}

}
