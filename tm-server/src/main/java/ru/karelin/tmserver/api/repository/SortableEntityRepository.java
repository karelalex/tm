package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.AbstractSortableEntity;

import java.util.List;

public interface SortableEntityRepository<T extends AbstractSortableEntity> extends SecuredEntityRepository<T> {



    List<T> findAllByUserIdOrderByStartDate(String userId);

    List<T> findAllByUserIdOrderByStartDateDesc(String userId);

    List<T> findAllByUserIdOrderByStatus(String userId);

    List<T> findAllByUserIdOrderByStatusDesc(String userId);

    List<T> findAllByUserIdOrderByFinishDate(String userId);

    List<T> findAllByUserIdOrderByFinishDateDesc(String userId);

    List<T> findAllByUserIdOrderByCreationDate(String userId);

    List<T> findAllByUserIdOrderByCreationDateDesc(String userId);
}
