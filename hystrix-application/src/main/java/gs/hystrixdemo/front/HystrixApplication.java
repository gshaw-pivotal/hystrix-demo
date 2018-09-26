package gs.hystrixdemo.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("gs.hystrixdemo.front")
@EnableCircuitBreaker
public class HystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixApplication.class, args);
	}
}
