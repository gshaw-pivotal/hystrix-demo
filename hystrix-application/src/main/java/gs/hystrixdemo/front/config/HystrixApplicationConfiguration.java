package gs.hystrixdemo.front.config;

import gs.hystrixdemo.front.controllers.HystrixApplicationController;
import gs.hystrixdemo.front.usecases.HystrixApplicationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HystrixApplicationConfiguration {

    @Bean
    public HystrixApplicationController hystrixApplicationController(HystrixApplicationUseCase useCase) {
        return new HystrixApplicationController(useCase);
    }

    @Bean
    public HystrixApplicationUseCase hystrixApplicationUseCase() {
        return new HystrixApplicationUseCase(new RestTemplate());
    }
}
