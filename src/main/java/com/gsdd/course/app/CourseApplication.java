package com.gsdd.course.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(
    info =
        @Info(
            title = "Course API",
            version = "2.0",
            description = "REST with Spring-Boot & SQLite",
            contact = @Contact(email = "alex.galvis.sistemas@gmail.com")))
public class CourseApplication {

  public static final String BASE_PACKAGE = "com.gsdd.course.app.";

  public static void main(String[] args) {
    SpringApplication.run(CourseApplication.class, args);
  }
}
