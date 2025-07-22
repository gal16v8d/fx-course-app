package com.gsdd.course.app.converter

import com.gsdd.course.app.model.CourseModel
import com.gsdd.course.app.persistence.entities.Course
import org.springframework.stereotype.Component

@Component
class CourseConverter : GenericConverter<Course, CourseModel> {
    override fun convertToDomain(entity: Course?): CourseModel? {
        return entity?.let {
                CourseModel(
                    courseId = it.courseId,
                    courseName = it.courseName
                )
        }
    }

    override fun convertToEntity(model: CourseModel?): Course?{
        return model?.let {
                Course(
                    courseId = it.courseId,
                    courseName = it.courseName
                )
        }
    }
}
