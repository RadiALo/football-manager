package com.radialo.footballmanager.service.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
