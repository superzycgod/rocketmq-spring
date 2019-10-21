package cn.zycgod.rocketmq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RocketmqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RocketmqDemoApplication.class, args);
	}

}
