package ru.karelin.tm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TaskDialog {
    private Scanner sc;
    private TaskManipulator taskManipulator = new TaskManipulator();
    private ProjectManipulator projectManipulator = new ProjectManipulator();
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    public TaskDialog(Scanner sc) {
        this.sc = sc;
    }

    public void createTask() {
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
        System.out.println("Enter project id where task will be added or leave it empty");
        String projectId = sc.nextLine();
        while(!projectId.isEmpty() && !projectManipulator.checkID(projectId)){
            System.out.println("Wrong project id try again or leave it empty");
            projectId = sc.nextLine();
        }

        taskManipulator.createTask(taskName, taskDescription, taskStartDate, taskFinishDate, projectId);

    }
}

