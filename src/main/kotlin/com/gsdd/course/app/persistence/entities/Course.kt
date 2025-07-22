package com.gsdd.course.app.persistence.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Entity
data class Course(
    @field:Id
    @field:Column(name = "courseId")
    @field:NotEmpty(message = "courseId should not be empty")
    val courseId: String,

    @field:Column(name = "courseName", unique = true, nullable = false)
    @field:NotEmpty(message = "courseName should not be empty")
    @field:Size(
        min = 3,
        message = "courseName should have at least three chars"
    )
    val courseName: String
) {
    // Required by JPA
    constructor(): this("", "")
}
