package sharding.yml;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "sharding.yml.dao")
public class ShardApp {

    public static void main(String[] args) {
        SpringApplication.run(ShardApp.class, args);
    }

}
