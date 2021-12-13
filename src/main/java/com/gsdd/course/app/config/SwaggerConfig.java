package com.gsdd.course.app.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.gsdd.course.app.CourseApplication;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage(CourseApplication.BASE_PACKAGE + "controller"))
        .paths(PathSelectors.regex("/courses.*")).build().apiInfo(apiInfo());
  }

  private Contact getContact() {
    return new Contact("A. Galvis", null, "alex.galvis.sistemas@gmail.com");
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("Course SQL REST", "REST con Spring-Boot & SQLite", "0.0.1.SNAPSHOT", null,
        getContact(), null, null, Collections.emptyList());
  }

}
