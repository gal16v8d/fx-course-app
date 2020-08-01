package co.com.gsdd.course.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@ComponentScan({ CourseApplication.BASE_PACKAGE + "config", CourseApplication.BASE_PACKAGE + "controller",
		CourseApplication.BASE_PACKAGE + "repository" })
public class CourseApplication {

	public static final String BASE_PACKAGE = "co.com.gsdd.course.app.";

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

}
