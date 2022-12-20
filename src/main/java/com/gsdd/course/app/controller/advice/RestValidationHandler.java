package com.gsdd.course.app.controller.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestValidationHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConflict(ConstraintViolationException e) {
    StringBuilder messages = new StringBuilder();
    e.getConstraintViolations()
        .forEach(cv -> messages.append(cv.getMessage()).append(System.lineSeparator()));
    return ResponseEntity.badRequest().body(messages.toString());
  }
}
