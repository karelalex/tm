package ru.karelin.tm.service;

import ru.karelin.tm.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskService {
    void createTask(String userId, String name, String description, Date startDate, Date finishDate, String projectId);

    void editTask(String userId, String id, String name, String description, Date startDate, Date finishDate, String projectId);

    List<Task> getTaskList(String userId);

    Task getTask(String userId, String taskId);

    List<Task> getTaskList(String userId, String projectId);

    void removeTask(String userId, String taskId);

    boolean checkID(String userId, String taskId);
}
