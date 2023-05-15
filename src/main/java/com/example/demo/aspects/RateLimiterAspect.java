package com.example.demo.aspects;

import com.example.demo.interfaces.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimiterAspect {

    private final Map<String, Integer> limitMap = new ConcurrentHashMap<>();
    private final Map<String, LocalDateTime> timeoutMap = new ConcurrentHashMap<>();

    @Pointcut("@annotation(rateLimit)")
    public void executeRateLimit(RateLimit rateLimit) {}

    @Around("executeRateLimit(rateLimit)")
    public Object rateLimit(ProceedingJoinPoint proceedingJoinPoint, RateLimit rateLimit) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();

        Integer limit = limitMap.get(ip);
        if (limit == null) {
            limitMap.put(ip, 1);
        } else if (limit > rateLimit.limit()) {
            LocalDateTime timeout = timeoutMap.get(ip);
            if (timeout == null || timeout.isBefore(LocalDateTime.now())) {
                limitMap.put(ip, 1);
                timeoutMap.remove(ip);
            } else {
                throw new RuntimeException("Rate limit exceeded. Try again in " + timeout.minusMinutes(LocalDateTime.now().getMinute()).getMinute() + " minutes.");
            }
        } else {
            limitMap.put(ip, limit + 1);
            timeoutMap.put(ip, LocalDateTime.now().plusMinutes(rateLimit.timeout()));
        }
        return proceedingJoinPoint.proceed();
    }

}
