package ru.karelin.tm.service;


import ru.karelin.tm.entity.Task;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.repository.TaskRepository;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(User currentUser, String name, String description, Date startDate, Date finishDate, String projectId) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setFinishDate(finishDate);
        task.setUserId(currentUser.getId());
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        taskRepository.persist(task);
    }

    public void editTask(User currentUser,String id, String name, String description, Date startDate, Date finishDate, String projectId) {
        Task task = taskRepository.findOne(id);
        checkSecurity(currentUser, task);
        if (!name.isEmpty()) task.setName(name);
        if (!description.isEmpty()) task.setDescription(description);
        if (startDate != null) task.setStartDate(startDate);
        if (finishDate != null) task.setFinishDate(finishDate);
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        taskRepository.merge(task);
    }

    public List<Task> getTaskList(User currentUser) {

        return filterList(currentUser,taskRepository.findAll());
    }

    public Task getTask(User currentUser, String taskId){
        Task task = taskRepository.findOne(taskId);
        checkSecurity(currentUser, task);
        return task;

    }

    public List<Task> getTaskList(User currentUser, String projectId) {
       return  filterList(currentUser,taskRepository.findAllByProjectId(projectId));
    }


    public void removeTask(User currentUser, String taskId) {
        Task task = taskRepository.findOne(taskId);
        checkSecurity(currentUser,task);
        if(task!=null)
            taskRepository.remove(task);
    }

    public boolean checkID(User currentUser, String taskId) {
        Task task = taskRepository.findOne(taskId);
        checkSecurity(currentUser, task);
        return task!=null;
    }

    private void checkSecurity(User user, Task task){
        if (task.getUserId().equals(user.getId())) return;
        else throw new SecurityException("Current user: " + user.getLogin() + " can not manipulate with task: " + task.getId());
    }
    private List<Task> filterList (User user, List<Task> list){
        Iterator<Task> iter = list.iterator();
        Task t;
        while (iter.hasNext()){
            t = iter.next();
            if (!t.getUserId().equals(user.getId())) iter.remove();
        }
        return list;
    }



}
