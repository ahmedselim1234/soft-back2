package com.hotel.booking.Asop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {

    @Before("execution(* com.hotel.booking.Service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Method execution started: " + joinPoint.getSignature());
    }

    @After("execution(* com.hotel.booking.Service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Method execution completed: " + joinPoint.getSignature());
    }

    @Around("execution(* com.hotel.booking.Service.*.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("PerformanceMonitoringAspect: " + joinPoint.getSignature() +
                " executed in " + (endTime - startTime) + " milliseconds");
        return result;
    }
}
