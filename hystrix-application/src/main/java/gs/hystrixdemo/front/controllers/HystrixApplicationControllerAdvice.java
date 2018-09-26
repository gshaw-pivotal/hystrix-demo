package gs.hystrixdemo.front.controllers;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import gs.hystrixdemo.front.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = HystrixApplicationWithAdviceController.class)
public class HystrixApplicationControllerAdvice extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = HystrixRuntimeException.class)
    ResponseEntity<String> handleHystrixTimeout(HystrixRuntimeException exception) {
        String fallbackExceptionMessage = "Default message";

        if (exception.getFallbackException().getCause().getMessage() != null) {
            fallbackExceptionMessage = exception.getFallbackException().getCause().getMessage();
        }

        return new ResponseEntity(fallbackExceptionMessage, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = CustomException.class)
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    void handleCustomerException() {}
}
