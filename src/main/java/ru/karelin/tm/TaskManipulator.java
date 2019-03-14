package ru.karelin.tm;

import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class TaskManipulator {
    private static Map<String, Task> tasks = Statics.tasks;

    public void createTask(String name, String description, Date startDate, Date finishDate, String projectId) {

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setFinishDate(finishDate);
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        tasks.put(task.getId(), task);
    }

    public void editTask(String id, String name, String description, Date startDate, Date finishDate, String projectId) {
        Task task = tasks.get(id);
        if (!name.isEmpty()) task.setName(name);
        if (!description.isEmpty()) task.setDescription(description);
        if (startDate != null) task.setStartDate(startDate);
        if (finishDate != null) task.setFinishDate(finishDate);
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        tasks.put(task.getId(), task);
    }

    public void showTaskList() {
        for (Map.Entry<String, Task> entry : tasks.entrySet()) {
            System.out.println("Task: " + entry.getKey());
            System.out.println("Task name: " + entry.getValue().getName());
            System.out.println("Task description: " + entry.getValue().getDescription());
            System.out.println("Project ID: " + entry.getValue().getProjectID());
            System.out.println();
        }
    }

    public void showTaskList(String projectId) {
        for (Map.Entry<String, Task> entry : tasks.entrySet()) {
            if (entry.getValue().getProjectID().equals(projectId)) {
                System.out.println("Task: " + entry.getKey());
                System.out.println("Task name: " + entry.getValue().getName());
                System.out.println("Task description: " + entry.getValue().getDescription());
                System.out.println("Task must be finished till " + entry.getValue().getFinishDate());
                System.out.println();
            }
        }
    }


    public void removeTask(String taskId) {
        tasks.remove(taskId);
    }

    public void removeTasksByProjectID(String projectId) {
        Iterator<Map.Entry<String, Task>> iterator = tasks.entrySet().iterator();
        Map.Entry<String, Task> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            if (entry.getValue().getProjectID().equals(projectId)) {
                iterator.remove();
            }
        }
    }


    public boolean checkID(String taskId) {
        return tasks.containsKey(taskId);
    }


}
