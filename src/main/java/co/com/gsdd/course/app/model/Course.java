package co.com.gsdd.course.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Generated;

@Generated
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
