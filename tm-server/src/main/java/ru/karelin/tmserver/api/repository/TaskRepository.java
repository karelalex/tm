package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.Task;

import java.util.List;

public interface TaskRepository extends SortableEntityRepository<Task> {
    List<Task> findAllByProjectId(String projectId);

    List<Task> findAllByProjectIdAndUserId(String projectId, String userId);

    List<Task> findAllByUserIdAndProjectIdOrderByStartDate(String userId, String projectId);

    List<Task> findAllByUserIdAndProjectIdOrderByStartDateDesc(String userId, String projectId);

    List<Task> findAllByUserIdAndProjectIdOrderByStatus(String userId, String projectId);

    List<Task> findAllByUserIdAndProjectIdOrderByStatusDesc(String userId, String projectId);

    List<Task> findAllByUserIdAndProjectIdOrderByFinishDate(String userId, String projectId);

    List<Task> findAllByUserIdAndProjectIdOrderByFinishDateDesc(String userId, String projectId);

    List<Task> findAllByUserIdAndProjectIdOrderByCreationDate(String userId, String projectId);

    List<Task> findAllByUserIdAndProjectIdOrderByCreationDateDesc(String userId, String projectId);

    List<Task> findAllByUserIdAndKeyword(String userId, String key);
}
