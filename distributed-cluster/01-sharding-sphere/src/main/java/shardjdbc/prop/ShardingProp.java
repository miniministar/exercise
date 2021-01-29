package shardjdbc.prop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "shardjdbc.prop.mapper")
public class ShardingProp {
	public static void main(String[] args) {
		SpringApplication.run(ShardingProp.class, args);
	}
}
