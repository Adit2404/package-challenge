package com.mobiquity.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Aspect-oriented logging class for method entry and exit points within the com.mobiquity package.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut definition to capture all methods within the com.mobiquity package and its sub-packages.
     */
    @Pointcut("within(com.mobiquity.*.*)")
    protected void allMethod() {
        // Empty method used to define a pointcut for methods within the specified package.
    }

    /**
     * Advice triggered before the execution of methods matched by the pointcut.
     * Logs method entry information.
     */
    @Before("allMethod()")
    public void logStartOfMethod(JoinPoint joinPoint) {
        logger.info("Inside method [" + joinPoint.getSignature().getName() + "] "
                + joinPoint.getTarget().getClass().getName() + " @ " + LocalDateTime.now().toString());
    }

    /**
     * Advice triggered after the execution of methods matched by the pointcut.
     * Logs method exit information.
     */
    @After("allMethod()")
    public void logEndOfMethod(JoinPoint joinPoint) {
        logger.info("Outside method [" + joinPoint.getSignature().getName() + "]  "
                + joinPoint.getTarget().getClass().getName() + " @ " + LocalDateTime.now().toString());
    }
}
