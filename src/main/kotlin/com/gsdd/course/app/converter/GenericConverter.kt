package com.gsdd.course.app.converter

interface GenericConverter<T, D> {
    fun convertToDomain(entity: T?): D?

    fun convertToEntity(model: D?): T?
}
