package com.gsdd.course.app.converter

import com.gsdd.course.app.model.CourseModel
import com.gsdd.course.app.persistence.entities.Course
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.function.Function

@Component
class CourseConverter : GenericConverter<Course, CourseModel> {
    override fun convertToDomain(entity: Course?): CourseModel? {
        return Optional.ofNullable<Course?>(entity)
            .map(
                Function { e: Course? ->
                    CourseModel(
                        courseId = e!!.courseId,
                        courseName = e.courseName)
                })
            .orElse(null)
    }

    override fun convertToEntity(model: CourseModel?): Course? {
        return Optional.ofNullable<CourseModel?>(model)
            .map(Function { m: CourseModel? ->
                Course(
                    courseId = m!!.courseId,
                    courseName = m.courseName)
            })
            .orElse(null)
    }
}
