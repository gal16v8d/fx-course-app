package com.gsdd.course.app.controller.advice

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.Map
import java.util.stream.Collectors

@ControllerAdvice
class RestValidationHandler {
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConflict(e: ConstraintViolationException): ResponseEntity<Any?> {
        return ResponseEntity.badRequest()
            .body<Any?>(
                e.constraintViolations.stream()
                    .map { obj: ConstraintViolation<*>? -> obj!!.message }
                    .collect(Collectors.joining(System.lineSeparator())))
    }

    @ExceptionHandler(Exception::class)
    fun defaultHandler(e: Exception, request: WebRequest?): ResponseEntity<Any?> {
        return ResponseEntity.internalServerError().body<Any?>(Map.of<String?, String?>("detail", e.message))
    }
}
