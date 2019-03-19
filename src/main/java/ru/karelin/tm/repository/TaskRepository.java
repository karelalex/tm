package ru.karelin.tm.repository;

import ru.karelin.tm.entity.Task;

import java.util.List;

public interface TaskRepository extends Repository<Task> {
    List<Task> findAllByProjectId(String projectId);

    List<Task> findAllByUserId(String userId);

    List<Task> findAllByProjectIdAndUserId(String projectId, String userId);

    Task findOneByIdAndUserId(String id, String userId);
}
