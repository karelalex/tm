package ru.karelin.tm.api.repository;

import ru.karelin.tm.entity.AbstractSortableEntity;

import java.util.List;

public interface SortableEntityRepository<T extends AbstractSortableEntity> extends SecuredEntityRepository<T> {
    List<T> findAllByUserId(String userId, String sortField, boolean isStraight);

    List<T> findAll(String sortField, boolean isStraight);
}
