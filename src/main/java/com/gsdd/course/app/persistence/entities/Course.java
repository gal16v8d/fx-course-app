package com.gsdd.course.app.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
