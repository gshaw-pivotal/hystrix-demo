package gs.hystrixdemo.front.controllers;

import gs.hystrixdemo.front.usecases.HystrixApplicationUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixApplicationController {

    private final HystrixApplicationUseCase useCase;

    public HystrixApplicationController(HystrixApplicationUseCase hystrixApplicationUseCase) {
        this.useCase = hystrixApplicationUseCase;
    }

    @GetMapping("/front/fast")
    public String fastEndpoint() {
        return useCase.executeFast();
    }

    @GetMapping("/front/slow-nh")
    public String slowEndpoint_withNoHystrix() {
        return useCase.executeSlow_noHystrix();
    }

    @GetMapping("/front/slow-wh-nf")
    public String slowEndpoint_withHystrix_noFallback() {
        return useCase.executeSlow_withHystrix_noFallback();
    }

    @GetMapping("/front/slow-wf")
    public String slowEndpoint_withHystrix_fallback() {
        return useCase.executeSlow_withHystrix_withFallback();
    }

    @GetMapping("/front/slow-wtf")
    public String slowEndpoint_withHystrix_throwingFallback() {
        return useCase.executeSlow_withHystrix_throwingFallback();
    }

    @GetMapping("/front/slow-capture")
    public String slowEndpoint_withHystrix_captureAllExceptions() {
        return useCase.executeSlow_withHystrix_captureExceptions_throwFallback();
    }

    @GetMapping("/front/slow-no-capture")
    public String slowEndpoint_withHystrix_ignoreException() {
        return useCase.executeSlow_withHystrix_noCaptureException_withFallback();
    }

}
