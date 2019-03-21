package ru.karelin.tm.api.repository;

import ru.karelin.tm.entity.Task;

import java.util.List;

public interface TaskRepository extends SecuredEntityRepository<Task> {
    List<Task> findAllByProjectId(String projectId);

    List<Task> findAllByProjectIdAndUserId(String projectId, String userId);


}
