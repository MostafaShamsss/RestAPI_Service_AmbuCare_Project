package com.example.demo.aspects;
import com.example.demo.exceptions.RateLimitException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class ErrorHandlingAspect {
    private final Logger logger = LoggerFactory.getLogger(ErrorHandlingAspect.class);

    /**
     * Log every exception
     * */
    @AfterThrowing(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", throwing = "ex")
    public void handleRestControllerException(Exception ex) {
        logger.error("Exception occurred: {}", ex.getMessage());
    }
    /**
     * Return 429 status code when
     * Rate limit exception thrown
     * Only activated in the PointCut related to @RateLimit
     * annotation
     * */
    @AfterThrowing(pointcut = "@annotation(com.example.demo.interfaces.RateLimit)", throwing = "ex")
    public ResponseEntity<String> handleRateLimitException(Throwable ex) {
        HttpStatus status = determineHttpStatus(ex);
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(status).body(errorMessage);
    }
    private HttpStatus determineHttpStatus(Throwable ex) {
        if (ex instanceof RateLimitException) {
            return HttpStatus.TOO_MANY_REQUESTS;
        }
        // Default status code for other exceptions
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
