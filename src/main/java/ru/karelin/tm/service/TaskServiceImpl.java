package ru.karelin.tm.service;


import ru.karelin.tm.entity.Task;
import ru.karelin.tm.repository.TaskRepository;

import java.util.Date;
import java.util.List;



public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(String userId, String name, String description, Date startDate, Date finishDate, String projectId) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setFinishDate(finishDate);
        task.setUserId(userId);
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        taskRepository.persist(task);
    }

    @Override
    public void editTask(String userId, String id, String name, String description, Date startDate, Date finishDate, String projectId) {
        Task task = taskRepository.findOneByIdAndUserId(id, userId);
        if (!name.isEmpty()) task.setName(name);
        if (!description.isEmpty()) task.setDescription(description);
        if (startDate != null) task.setStartDate(startDate);
        if (finishDate != null) task.setFinishDate(finishDate);
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        taskRepository.merge(task);
    }

    @Override
    public List<Task> getTaskList(String userId) {

        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public Task getTask(String userId, String taskId){
        Task task = taskRepository.findOneByIdAndUserId(taskId, userId);
        return task;

    }

    @Override
    public List<Task> getTaskList(String userId, String projectId) {
       return taskRepository.findAllByProjectIdAndUserId(projectId, userId);
    }


    @Override
    public void removeTask(String userId, String taskId) {
        Task task = taskRepository.findOneByIdAndUserId(taskId, userId);
        if(task!=null)
            taskRepository.remove(task);
    }

    @Override
    public boolean checkID(String userId, String taskId) {
        Task task = taskRepository.findOneByIdAndUserId(taskId, userId);
        return task!=null;
    }




}
