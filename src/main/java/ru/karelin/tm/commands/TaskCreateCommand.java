package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class TaskCreateCommand  extends AbstractCommand {
    public TaskCreateCommand(Bootstrap bootstrap) {
        super("ct",
                "Starts create new task dialog. Task will be created inside " +
                        "specified project or project ID will be asked later.",
                bootstrap);
    }

    @Override
    public void execute(String... params) {
        DateFormat dateFormat = bootstrap.getDateFormat();
        ProjectService projectService = bootstrap.getProjectService();
        TaskService taskService = bootstrap.getTaskService();
        String projectId = params[0];
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
}
