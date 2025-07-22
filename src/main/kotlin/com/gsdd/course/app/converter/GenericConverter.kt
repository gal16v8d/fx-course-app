package com.gsdd.course.app.converter

import com.gsdd.course.app.model.CourseModel

interface GenericConverter<T, D> {
    fun convertToDomain(entity: T?): D?

    fun convertToEntity(model: D?): T?
}
