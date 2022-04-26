package com.gsdd.course.app.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Generated
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Course {

    @NotEmpty(message = "courseId should not be empty")
    @Id
    @Column(name = "courseId")
    private String courseId;

    @NotEmpty(message = "courseName should not be empty")
    @Size(min = 3, message = "courseName should have at least three chars")
    @Column(name = "courseName", unique = true, nullable = false)
    private String courseName;
}
