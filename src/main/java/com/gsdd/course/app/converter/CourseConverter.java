package com.gsdd.course.app.converter;

import com.gsdd.course.app.model.CourseModel;
import com.gsdd.course.app.persistence.entities.Course;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter implements GenericConverter<Course, CourseModel> {

  @Override
  public CourseModel convertToDomain(Course entity) {
    return Optional.ofNullable(entity)
        .map(
            e ->
                CourseModel.builder()
                    .courseId(e.getCourseId())
                    .courseName(e.getCourseName())
                    .build())
        .orElse(null);
  }

  @Override
  public Course convertToEntity(CourseModel model) {
    return Optional.ofNullable(model)
        .map(m -> Course.builder().courseId(m.getCourseId()).courseName(m.getCourseName()).build())
        .orElse(null);
  }
}
