package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.AbstractSortableEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface SortableEntityRepository<T extends AbstractSortableEntity> extends SecuredEntityRepository<T> {



    List<T> findAllByUserIdOrderByStartDate(String userId, EntityManager em);

    List<T> findAllByUserIdOrderByStartDateDesc(String userId, EntityManager em);

    List<T> findAllByUserIdOrderByStatus(String userId, EntityManager em);

    List<T> findAllByUserIdOrderByStatusDesc(String userId, EntityManager em);

    List<T> findAllByUserIdOrderByFinishDate(String userId, EntityManager em);

    List<T> findAllByUserIdOrderByFinishDateDesc(String userId, EntityManager em);

    List<T> findAllByUserIdOrderByCreationDate(String userId, EntityManager em);

    List<T> findAllByUserIdOrderByCreationDateDesc(String userId, EntityManager em);
}
