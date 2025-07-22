package com.gsdd.course.app.controller

import com.gsdd.course.app.converter.GenericConverter
import com.gsdd.course.app.model.CourseModel
import com.gsdd.course.app.persistence.entities.Course
import com.gsdd.course.app.repository.CourseRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RefreshScope
@RestController
@RequestMapping("courses")
open class CourseController @Autowired constructor(
    private val courseRepository: CourseRepository,
    private val courseConverter: GenericConverter<Course, CourseModel>,
    @Value("\${service.welcome.message}")
    private val welcomeMessage: String) {

    @GetMapping("/welcome")
    fun getWelcomeMsg(): String = welcomeMessage

    @GetMapping
    fun getAll(): ResponseEntity<List<CourseModel>> {
        val courses = courseRepository.findAll(Sort.by("courseId"))
            .mapNotNull { courseConverter.convertToDomain(it) }
        return ResponseEntity.ok(courses)
    }

    @GetMapping("{courseId}")
    fun getById(@PathVariable("courseId") courseId: String): ResponseEntity<CourseModel?> {
        val optionalCourse = courseRepository.findById(courseId)
        return optionalCourse.map { entity ->
            courseConverter.convertToDomain(entity)
        }.map { model ->
            ResponseEntity.ok(model)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun saveCourse(@RequestBody course: @Valid CourseModel): ResponseEntity<CourseModel> {
        val entityToSave = courseConverter.convertToEntity(course)
        return entityToSave?.let {
            val savedEntity = courseRepository.saveAndFlush(it)
            val savedModel = courseConverter.convertToDomain(savedEntity)
            savedModel?.let { model ->
                ResponseEntity.ok(model)
            } ?: ResponseEntity.badRequest().build()
        } ?: ResponseEntity.badRequest().build()
    }

    @PutMapping("{courseId}")
    fun updateCourse(
        @PathVariable("courseId") courseId: String, @RequestBody course: @Valid CourseModel
    ): ResponseEntity<CourseModel> {
        val optionalCourse = courseRepository.findById(courseId)

        return if (optionalCourse.isPresent) {
            val entityToSave = courseConverter.convertToEntity(course)
            entityToSave?.let {
                val updatedEntity = courseRepository.saveAndFlush(it)
                val updatedModel = courseConverter.convertToDomain(updatedEntity)
                updatedModel?.let { model ->
                    ResponseEntity.ok(model)
                } ?: ResponseEntity.badRequest().build()
            } ?: ResponseEntity.badRequest().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("{courseId}")
    fun deleteCourse(@PathVariable("courseId") courseId: String): ResponseEntity<Any> {
        val optionalCourse = courseRepository.findById(courseId)

        return if (optionalCourse.isPresent) {
            courseRepository.delete(optionalCourse.get())
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
