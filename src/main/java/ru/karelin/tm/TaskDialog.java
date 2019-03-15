package ru.karelin.tm;

import ru.karelin.tm.service.ProjectManipulator;
import ru.karelin.tm.service.TaskManipulator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TaskDialog {
    private Scanner sc;
    private TaskManipulator taskManipulator = new TaskManipulator();
    private ProjectManipulator projectManipulator = new ProjectManipulator();
    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    public TaskDialog(Scanner sc) {
        this.sc = sc;
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
        while (!projectId.isEmpty() && !projectManipulator.checkID(projectId)) {
            System.out.println("Wrong project id try again or leave it empty");
            projectId = sc.nextLine();
        }

        taskManipulator.createTask(taskName, taskDescription, taskStartDate, taskFinishDate, projectId);

    }

    public void editTask(String taskId) {
        if (!taskManipulator.checkID(taskId)) {
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
        while (!projectId.isEmpty() && !projectManipulator.checkID(projectId)) {
            System.out.println("Wrong project id try again or leave it empty");
            projectId = sc.nextLine();
        }

        taskManipulator.editTask(taskId, taskName, taskDescription, taskStartDate, taskFinishDate, projectId);

    }
    public void showTaskList(String projectId){
        if (projectId.isEmpty()) taskManipulator.showTaskList();
        else if(projectManipulator.checkID(projectId)) taskManipulator.showTaskList(projectId);
        else System.out.println("Wrong Project ID");
    }
    public void showTask(String taskId){
        if(!taskManipulator.checkID(taskId)){
            System.out.println("Wrong ID");
            return;
        }
        taskManipulator.showTask(taskId);

    }
    public void removeTask(String taskId){
        taskManipulator.removeTask(taskId);
    }
}

