package com.gsdd.course.app.converter;

public interface GenericConverter<T, D> {

    D convertToDomain(T entity);

    T convertToEntity(D model);
}
