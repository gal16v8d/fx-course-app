package co.com.gsdd.course.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.gsdd.course.app.model.Course;
import co.com.gsdd.course.app.repository.CourseRepository;

@RefreshScope
@RestController
@RequestMapping("courses")
public class CourseController {
	
	@Value("${service.welcome.message}")
	private String welcomeMessage;

	private final CourseRepository courseRepository;

	@Autowired
	public CourseController(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@GetMapping("/welcome")
	public String getWelcomeMsg() {
		return welcomeMessage;
	}

	@GetMapping
	public List<Course> getCourses() {
		return courseRepository.findAll(Sort.by("courseId"));
	}

	@GetMapping("{courseId}")
	public ResponseEntity<Course> getSpecificCourse(@PathVariable("courseId") String courseId) {
		return courseRepository.findById(courseId).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public Course saveCourse(@RequestBody Course course) {
		return courseRepository.saveAndFlush(course);
	}

	@PutMapping("{courseId}")
	public ResponseEntity<Course> updateCourse(@PathVariable("courseId") String courseId, @RequestBody Course course) {
		return courseRepository.findById(courseId).map(oldCourse -> courseRepository.saveAndFlush(course))
				.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("{courseId}")
	public ResponseEntity<Course> deleteCourse(@PathVariable("courseId") String courseId) {
		ResponseEntity<Course> response;
		Optional<Course> courseOp = courseRepository.findById(courseId);
		if (courseOp.isPresent()) {
			courseRepository.delete(courseOp.get());
			response = ResponseEntity.ok().build();
		} else {
			response = ResponseEntity.notFound().build();
		}
		return response;
	}

}
