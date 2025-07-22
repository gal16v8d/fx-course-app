package com.gsdd.course.app.config

import com.gsdd.course.app.CourseApplication
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

@Aspect
@Component
class RequestLogger {
    companion object {
        val log  = logger()
    }

    @Around("execution(* com.gsdd.course.app.controller.CourseController.*(..))")
    @Throws(Throwable::class)
    fun around(joinPoint: ProceedingJoinPoint): Any? {
        val nameJp = joinPoint.signature.name
        val startTime = System.nanoTime()
        log.info("Before: {}", nameJp)
        val o = joinPoint.proceed()
        log.info("After: {}", nameJp)
        val timeTaken = System.nanoTime() - startTime
        log.info(
            "request {} took {} ms",
            nameJp,
            TimeUnit.MILLISECONDS.convert(timeTaken, TimeUnit.NANOSECONDS)
        )
        return o
    }
}
