package gs.hystrixdemo.back.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class BackendApplicationController {

    private final int slowEndpointDelay;

    public BackendApplicationController(int slowEndpointDelay) {
        this.slowEndpointDelay = slowEndpointDelay;
    }

    @GetMapping("/backend/fast")
    public String fastEndpoint() {
        return "I am fast";
    }

    @GetMapping("/backend/slow")
    public String slowBackend() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(slowEndpointDelay);
        return "I am SLOW";
    }
}
