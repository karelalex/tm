package ru.karelin.tm;

import ru.karelin.tm.entity.Task;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TaskDialog {
    private Scanner sc;
    private TaskService taskService;
    private ProjectService projectService;
    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    public TaskDialog(Scanner sc, TaskService taskService, ProjectService projectService) {
        this.sc = sc;
        this.taskService = taskService;
        this.projectService = projectService;
    }

    public void createTask(){
        createTask("");
    }
    public void createTask(String projectId) {
        System.out.println("Enter task name");
        String taskName = sc.nextLine();
        System.out.println("Enter task description");
        String taskDescription = sc.nextLine();
        String date;
        Date taskStartDate;
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or leave empty for today");
            date = sc.nextLine();
            if (date.isEmpty()) {
                taskStartDate = new Date();
                break;
            } else {
                try {
                    taskStartDate = dateFormat.parse(date);
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        Date taskFinishDate;
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY)");
            date = sc.nextLine();
            try {
                taskFinishDate = dateFormat.parse(date);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (projectId.isEmpty()) {
            System.out.println("Enter project id where task will be added or leave it empty");
            projectId = sc.nextLine();
        }
        while (!projectId.isEmpty() && !projectService.checkID(projectId)) {
            System.out.println("Wrong project id try again or leave it empty");
            projectId = sc.nextLine();
        }

        taskService.createTask(taskName, taskDescription, taskStartDate, taskFinishDate, projectId);

    }

    public void editTask(String taskId) {
        if (!taskService.checkID(taskId)) {
            System.out.println("Wrong ID!");
            return;
        }
        System.out.println("Enter new task name or just press enter if you do not want to change it");
        String taskName = sc.nextLine();
        System.out.println("Enter new task description or just press enter if you do not want to change it");
        String taskDescription = sc.nextLine();
        String date;
        Date taskStartDate;
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or just press enter if you do not want to change it");
            date = sc.nextLine();
            if (date.isEmpty()) {
                taskStartDate = null;
                break;
            } else {
                try {
                    taskStartDate = dateFormat.parse(date);
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        Date taskFinishDate;
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY) or just press enter if you do not want to change it");
            date = sc.nextLine();
            if (date.isEmpty()) {
                taskFinishDate = null;
                break;
            } else {
                try {
                    taskFinishDate = dateFormat.parse(date);
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Enter new project id for task or just press enter if you do not want to change it");
        String projectId = sc.nextLine();
        while (!projectId.isEmpty() && !projectService.checkID(projectId)) {
            System.out.println("Wrong project id try again or leave it empty");
            projectId = sc.nextLine();
        }

        taskService.editTask(taskId, taskName, taskDescription, taskStartDate, taskFinishDate, projectId);

    }
    public void showTaskList(String projectId){
        List<Task> tasks;
        boolean showProjectId=true;
        if (projectId.isEmpty()) tasks=taskService.getTaskList();
        else if(projectService.checkID(projectId)) {
            tasks=taskService.getTaskList(projectId);
            showProjectId=false;
        }
        else {
            System.out.println("Wrong Project ID");
            return;
        }
        for ( Task t : tasks) {
            System.out.println("Task: " + t.getId());
            System.out.println("Task name: " + t.getName());
            System.out.println("Task description: " + t.getDescription());
            if (showProjectId) System.out.println("Project ID: " + t.getProjectID());
            System.out.println();
        }
    }
    public void showTask(String taskId){
        if(!taskService.checkID(taskId)){
            System.out.println("Wrong ID");
            return;
        }
        Task task = taskService.getTask(taskId);
        System.out.println("Task name: " + task.getName());
        System.out.println("Task description: " + task.getDescription());
        System.out.println("Task start date: " + dateFormat.format(task.getStartDate()));
        System.out.println("Task finish date " + dateFormat.format(task.getFinishDate()));
        System.out.println("Project ID: " + task.getProjectID());
        System.out.println();

    }
    public void removeTask(String taskId){
        taskService.removeTask(taskId);
    }
}

