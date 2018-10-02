package gs.hystrixdemo.front.usecases;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import gs.hystrixdemo.front.exceptions.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HystrixApplicationUseCase {

    private RestTemplate restTemplate;

    public HystrixApplicationUseCase(RestTemplate template) {
        this.restTemplate = template;
    }

    public String executeFast() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:3001/backend/fast",
                String.class
        );

        return responseEntity.getBody();
    }

    public String executeSlow_noHystrix() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:3001/backend/slow",
                String.class
        );

        return responseEntity.getBody();
    }

    @HystrixCommand(
            commandKey = "slowNoFallback"
    )
    public String executeSlow_withHystrix_noFallback() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:3001/backend/slow",
                String.class
        );

        return responseEntity.getBody();
    }

    @HystrixCommand(
            commandKey = "slowFallback",
            fallbackMethod = "hystrixFallback"
    )
    public String executeSlow_withHystrix_withFallback() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:3001/backend/slow",
                String.class
        );

        return responseEntity.getBody();
    }

    @HystrixCommand(
            commandKey = "slowThrowFallback",
            fallbackMethod = "hystrixThrowFallback"
    )
    public String executeSlow_withHystrix_throwingFallback() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:3001/backend/slow",
                String.class
        );

        return responseEntity.getBody();
    }

    @HystrixCommand(
            commandKey = "slowCaptureWithThrowFallback",
            fallbackMethod = "hystrixThrowFallback"
    )
    public String executeSlow_withHystrix_captureExceptions_throwFallback() {
        throw new CustomException();
    }

    @HystrixCommand(
            commandKey = "slowNoCaptureWithFallback",
            fallbackMethod = "hystrixFallback",
            ignoreExceptions = CustomException.class
    )
    public String executeSlow_withHystrix_noCaptureException_withFallback() {
        throw new CustomException();
    }

    String hystrixFallback() {
        return "Primary is down! Use backup instead.";
    }

    String hystrixThrowFallback() throws Exception {
        throw new Exception("Not good");
    }
}
