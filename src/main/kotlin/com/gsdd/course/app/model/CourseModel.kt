package com.gsdd.course.app.model

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class CourseModel(

    @field:NotEmpty(message = "courseId should not be empty")
    val courseId: String,

    @field:NotEmpty(message = "courseName should not be empty")
    @field:Size(min = 3, message = "courseName should have at least three chars")
    val courseName: String
)
