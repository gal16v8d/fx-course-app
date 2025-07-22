package com.gsdd.course.app.repository

import com.gsdd.course.app.persistence.entities.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, String>
