package com.gsdd.course.app.config;

import com.gsdd.course.app.CourseApplication;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RequestLogger {

  @Around("execution(* " + CourseApplication.BASE_PACKAGE + "controller.CourseController.*(..))")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    Object o = null;
    String nombreJP = joinPoint.getSignature().getName();
    long startTime = System.nanoTime();
    log.info("Before: {}", nombreJP);
    o = joinPoint.proceed();
    log.info("After: {}", nombreJP);
    long timeTaken = System.nanoTime() - startTime;
    log.info(
        "request {} took {} ms",
        nombreJP,
        TimeUnit.MILLISECONDS.convert(timeTaken, TimeUnit.NANOSECONDS));
    return o;
  }
}
