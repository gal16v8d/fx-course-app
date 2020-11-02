package co.com.gsdd.course.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.gsdd.course.app.converter.GenericConverter;
import co.com.gsdd.course.app.model.CourseModel;
import co.com.gsdd.course.app.persistence.entities.Course;
import co.com.gsdd.course.app.repository.CourseRepository;

@RefreshScope
@RestController
@RequestMapping("courses")
public class CourseController {

	@Value("${service.welcome.message}")
	private String welcomeMessage;

	private final CourseRepository courseRepository;
	private final GenericConverter<Course, CourseModel> courseConverter;

	@Autowired
	public CourseController(GenericConverter<Course, CourseModel> courseConverter, CourseRepository courseRepository) {
		this.courseConverter = courseConverter;
		this.courseRepository = courseRepository;
	}

	@GetMapping("/welcome")
	public String getWelcomeMsg() {
		return welcomeMessage;
	}

	private CourseModel defineModelWithLinks(Course entity) {
		CourseModel model = courseConverter.convertToDomain(entity);
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(CourseController.class).getById(model.getCourseId())).withSelfRel();
		model.add(link);
		return model;
	}

	@GetMapping
	public ResponseEntity<CollectionModel<CourseModel>> getAll() {
		List<CourseModel> courses = courseRepository.findAll(Sort.by("courseId")).stream()
				.map(this::defineModelWithLinks).collect(Collectors.toList());
		Link link = WebMvcLinkBuilder.linkTo(CourseController.class).withSelfRel();
		CollectionModel<CourseModel> result = CollectionModel.of(courses, link);
		return ResponseEntity.ok(result);
	}

	@GetMapping("{courseId}")
	public ResponseEntity<CourseModel> getById(@PathVariable("courseId") String courseId) {
		return courseRepository.findById(courseId).map(this::defineModelWithLinks).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public CourseModel saveCourse(@Valid @RequestBody CourseModel course) {
		return defineModelWithLinks(courseRepository.saveAndFlush(courseConverter.convertToEntity(course)));
	}

	@PutMapping("{courseId}")
	public ResponseEntity<CourseModel> updateCourse(@PathVariable("courseId") String courseId,
			@Valid @RequestBody CourseModel course) {
		return courseRepository.findById(courseId)
				.map(oldCourse -> courseRepository.saveAndFlush(courseConverter.convertToEntity(course)))
				.map(this::defineModelWithLinks).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("{courseId}")
	public ResponseEntity<CourseModel> deleteCourse(@PathVariable("courseId") String courseId) {
		ResponseEntity<CourseModel> response;
		Optional<Course> courseOp = courseRepository.findById(courseId);
		if (courseOp.isPresent()) {
			courseRepository.delete(courseOp.get());
			response = ResponseEntity.noContent().build();
		} else {
			response = ResponseEntity.notFound().build();
		}
		return response;
	}

}
