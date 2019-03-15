package ru.karelin.tm.service;


import ru.karelin.tm.entity.Task;
import ru.karelin.tm.repository.TaskRepository;
import java.util.Date;
import java.util.List;



public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(String name, String description, Date startDate, Date finishDate, String projectId) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setFinishDate(finishDate);
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        taskRepository.persist(task);
    }

    public void editTask(String id, String name, String description, Date startDate, Date finishDate, String projectId) {
        Task task = taskRepository.findOne(id);
        if (!name.isEmpty()) task.setName(name);
        if (!description.isEmpty()) task.setDescription(description);
        if (startDate != null) task.setStartDate(startDate);
        if (finishDate != null) task.setFinishDate(finishDate);
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        taskRepository.merge(task);
    }

    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    public Task getTask(String taskId){
        Task task = taskRepository.findOne(taskId);
        return task;

    }

    public List<Task> getTaskList(String projectId) {
       return taskRepository.findAllByProjectId(projectId);
    }


    public void removeTask(String taskId) {
        Task task = taskRepository.findOne(taskId);
        if(task!=null)
            taskRepository.remove(task);
    }

    public boolean checkID(String taskId) {
        Task task = taskRepository.findOne(taskId);
        return task!=null;
    }


}
