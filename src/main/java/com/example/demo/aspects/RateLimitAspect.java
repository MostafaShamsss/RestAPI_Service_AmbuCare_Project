package com.example.demo.aspects;

import com.example.demo.api.rateLimiting.SimpleRateLimiter;
import com.example.demo.exceptions.TooManyRequestsException;
import com.example.demo.interfaces.RateLimit;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.Method;

@Component
@Aspect
public class RateLimitAspect {

    private final SimpleRateLimiter rateLimiter;
    private final HttpServletRequest request;

    @Autowired
    public RateLimitAspect(HttpServletRequest request) {
        this.request = request;
        this.rateLimiter = new SimpleRateLimiter(1000); // Set your desired limit here
    }

    @Around("@annotation(com.example.demo.annotation.RateLimit)")
    public Object rateLimiter(ProceedingJoinPoint joinPoint) throws Throwable {
        String ipAddress = request.getRemoteAddr();

        if (!rateLimiter.tryAcquire(ipAddress)) {
            throw new TooManyRequestsException("Too many requests");
        }

        return joinPoint.proceed();
    }

    private RateLimit getRateLimitAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (!method.isAnnotationPresent(RateLimit.class)) {
            method = joinPoint.getTarget().getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
        }
        return method.getAnnotation(RateLimit.class);
    }
}
