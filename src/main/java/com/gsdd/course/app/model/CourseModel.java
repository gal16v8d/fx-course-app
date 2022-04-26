package com.gsdd.course.app.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import org.springframework.hateoas.RepresentationModel;

@Generated
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CourseModel extends RepresentationModel<CourseModel> {

    @NotEmpty(message = "courseId should not be empty")
    @Id
    @Column(name = "courseId")
    private String courseId;

    @NotEmpty(message = "courseName should not be empty")
    @Size(min = 3, message = "courseName should have at least three chars")
    @Column(name = "courseName", unique = true, nullable = false)
    private String courseName;
}
