package com.example.demo.aspects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    /**
     * the within() pointcut expression is used to match join points within a specific type or types.
     * It allows you to define a pattern that matches a certain set of classes or packages.
     * within(TypePattern) - Matches join points within the specified type or types.
     *
     * TypePattern: Refers to the pattern used to match types.
     * It can be a fully qualified class name, a package name, or a combination of both.
     * Wildcards (*) and other pattern matching techniques can be used.
     * */
    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing method: {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} executed successfully. Result: {}", joinPoint.getSignature().toShortString(), result);
    }
}
