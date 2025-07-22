package com.gsdd.course.app

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(
    info = Info(
        title = "Course API",
        version = "2.0",
        description = "REST with Spring-Boot & SQLite",
        contact = Contact(email = "alex.galvis.sistemas@gmail.com")
    )
)
object CourseApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(CourseApplication::class.java, *args)
    }
}
