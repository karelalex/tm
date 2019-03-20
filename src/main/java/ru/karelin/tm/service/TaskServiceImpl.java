package ru.karelin.tm.service;


import ru.karelin.tm.entity.Task;
import ru.karelin.tm.repository.TaskRepository;

import java.util.Date;
import java.util.List;



public final class TaskServiceImpl implements TaskService {

    final private TaskRepository taskRepository;

    public TaskServiceImpl(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(final String userId, final String name, final String description, final Date startDate, final Date finishDate, final String projectId) {
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setFinishDate(finishDate);
        task.setUserId(userId);
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        taskRepository.persist(task);
    }

    @Override
    public void editTask(final String userId, final String id, final String name, final String description, final Date startDate, final Date finishDate, final String projectId) {
        final Task task = taskRepository.findOneByIdAndUserId(id, userId);
        if (!name.isEmpty()) task.setName(name);
        if (!description.isEmpty()) task.setDescription(description);
        if (startDate != null) task.setStartDate(startDate);
        if (finishDate != null) task.setFinishDate(finishDate);
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        taskRepository.merge(task);
    }

    @Override
    public List<Task> getTaskList(final String userId) {

        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public Task getTask(final String userId, final String taskId){
        return taskRepository.findOneByIdAndUserId(taskId, userId);


    }

    @Override
    public List<Task> getTaskList(final String userId, final String projectId) {
       return taskRepository.findAllByProjectIdAndUserId(projectId, userId);
    }


    @Override
    public void removeTask(final String userId, final String taskId) {
        final Task task = taskRepository.findOneByIdAndUserId(taskId, userId);
        if(task!=null)
            taskRepository.remove(task);
    }

    @Override
    public boolean checkID(final String userId, final String taskId) {
        final Task task = taskRepository.findOneByIdAndUserId(taskId, userId);
        return task!=null;
    }




}
