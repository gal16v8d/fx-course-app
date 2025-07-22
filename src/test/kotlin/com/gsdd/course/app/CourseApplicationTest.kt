package com.gsdd.course.app

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
internal class CourseApplicationTest {
    @Test
    fun contextLoads(context: ApplicationContext?) {
        Assertions.assertNotNull(context)
    }
}
