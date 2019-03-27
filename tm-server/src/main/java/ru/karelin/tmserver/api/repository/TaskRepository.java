package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.Task;

import java.util.List;

public interface TaskRepository extends SortableEntityRepository<Task> {
    List<Task> findAllByProjectId(String projectId);

    List<Task> findAllByProjectIdAndUserId(String projectId, String userId);

    List<Task> findAllByProjectId(String projectId, String sortField, boolean isStraight);

    List<Task> findAllByProjectIdAndUserId(String projectId, String userId, String sortField, boolean isStraight);

    List<Task> findAllByUserIdAndKeyword(String userId, String key);
}
