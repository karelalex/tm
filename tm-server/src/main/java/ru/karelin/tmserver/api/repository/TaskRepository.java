package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.Task;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

public interface TaskRepository extends SortableEntityRepository<Task> {



    List<Task> findAllByProjectId(String projectId, EntityManager em);

    List<Task> findAllByProjectIdAndUserId(String projectId, String userId, EntityManager em);

    List<Task> findAllByUserIdAndProjectIdOrderByStartDate(String userId, String projectId, EntityManager em);

    List<Task> findAllByUserIdAndProjectIdOrderByStartDateDesc(String userId, String projectId, EntityManager em);

    List<Task> findAllByUserIdAndProjectIdOrderByStatus(String userId, String projectId, EntityManager em);

    List<Task> findAllByUserIdAndProjectIdOrderByStatusDesc(String userId, String projectId, EntityManager em);

    List<Task> findAllByUserIdAndProjectIdOrderByFinishDate(String userId, String projectId, EntityManager em);

    List<Task> findAllByUserIdAndProjectIdOrderByFinishDateDesc(String userId, String projectId, EntityManager em);

    List<Task> findAllByUserIdAndProjectIdOrderByCreationDate(String userId, String projectId, EntityManager em);

    List<Task> findAllByUserIdAndProjectIdOrderByCreationDateDesc(String userId, String projectId, EntityManager em);

    List<Task> findAllByUserIdAndKeyword(String userId, String key, EntityManager em);
}
