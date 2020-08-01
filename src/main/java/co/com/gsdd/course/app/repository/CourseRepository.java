package co.com.gsdd.course.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.gsdd.course.app.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

}
