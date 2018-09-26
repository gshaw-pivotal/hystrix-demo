package gs.hystrixdemo.back.config;

import gs.hystrixdemo.back.controllers.BackendApplicationController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackendApplicationConfiguration {

    @Value("${delay.slow}")
    public int slowEndpointDelay;

    @Bean
    public BackendApplicationController backendApplicationController() {
        return new BackendApplicationController(slowEndpointDelay);
    }
}
