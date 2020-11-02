package co.com.gsdd.course.app.converter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import co.com.gsdd.course.app.model.CourseModel;
import co.com.gsdd.course.app.persistence.entities.Course;

@Component
public class CourseConverter implements GenericConverter<Course, CourseModel> {

	@Override
	public CourseModel convertToDomain(Course entity) {
		return Optional.ofNullable(entity)
				.map(e -> CourseModel.builder().courseId(e.getCourseId()).courseName(e.getCourseName()).build())
				.orElse(null);
	}

	@Override
	public Course convertToEntity(CourseModel model) {
		return Optional.ofNullable(model)
				.map(m -> Course.builder().courseId(m.getCourseId()).courseName(m.getCourseName()).build())
				.orElse(null);
	}

}
