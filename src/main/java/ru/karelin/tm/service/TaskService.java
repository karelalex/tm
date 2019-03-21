package ru.karelin.tm.service;

import ru.karelin.tm.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskService extends SecuredEntitiesService<Task> {
    void create(String userId, String name, String description, Date startDate, Date finishDate, String projectId);

    void edit(String userId, String id, String name, String description, Date startDate, Date finishDate, String projectId);

    List<Task> getListByProjectId(String userId, String projectId);

}
