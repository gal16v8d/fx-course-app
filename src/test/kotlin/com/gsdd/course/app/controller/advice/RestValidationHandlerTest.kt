package com.gsdd.course.app.controller.advice

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

internal class RestValidationHandlerTest {
    private var handler: RestValidationHandler? = null

    @BeforeEach
    fun setUp() {
        handler = RestValidationHandler()
    }

    @Test
    fun handleConflict_shouldHandleNoViolations() {
        val violations: MutableSet<ConstraintViolation<Any?>?> = HashSet()
        val exception = ConstraintViolationException(violations)

        val responseEntity = handler!!.handleConflict(exception)

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.statusCode)
        Assertions.assertEquals("", responseEntity.getBody())
    }

    @Test
    fun handleExceptionTest() {
        val exc = Exception("Error")
        val result = handler!!.defaultHandler(exc, null)
        Assertions.assertEquals(
            HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
            result.statusCode
        )
    }
}
