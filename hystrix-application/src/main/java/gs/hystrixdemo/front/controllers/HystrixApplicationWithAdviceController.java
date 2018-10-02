package gs.hystrixdemo.front.controllers;

import gs.hystrixdemo.front.usecases.HystrixApplicationUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixApplicationWithAdviceController {

    private final HystrixApplicationUseCase useCase;

    public HystrixApplicationWithAdviceController(HystrixApplicationUseCase hystrixApplicationUseCase) {
        this.useCase = hystrixApplicationUseCase;
    }

    @GetMapping("/front/slow-advice-nf")
    public String slowEndpoint_withHystrix_noFallback() {
        return useCase.executeSlow_withHystrix_noFallback();
    }

    @GetMapping("/front/slow-advice-tf")
    public String slowEndpoint_withHystrix_throwingFallback() {
        return useCase.executeSlow_withHystrix_throwingFallback();
    }

    @GetMapping("/front/slow-advice-no-capture")
    public String slowEndpoint_withHystrix_ignoreException() {
        return useCase.executeSlow_withHystrix_noCaptureException_withFallback();
    }
}
