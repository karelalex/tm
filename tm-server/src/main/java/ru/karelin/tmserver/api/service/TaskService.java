package ru.karelin.tmserver.api.service;

import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;

import java.util.Date;
import java.util.List;

public interface TaskService extends SecuredEntityService<Task> {
    void create(String userId, String name, String description, Date startDate, Date finishDate, String projectId);

    void edit(String userId, String id, String name, String description, Date startDate, Date finishDate, String projectId, Status status);

    List<Task> getListByProjectId(String userId, String projectId);

    List<Task> getSortedListByProjectId(String userId, String projectId, String sortField, boolean isStraight);

    List<Task> getSortedList(String userId, String sortField, boolean isStraight);

    List<Task> getListByKeyword(String userId, String keyword);
}
