package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class TaskEditCommand extends AbstractCommand {
    public TaskEditCommand(Bootstrap bootstrap) {
        super("et", "starts edit task dialog for specified task", bootstrap);
    }

    @Override
    public void execute(String... params) {
        TaskService taskService = bootstrap.getTaskService();
        String taskId = params[0];
        DateFormat dateFormat = bootstrap.getDateFormat();
        ProjectService projectService = bootstrap.getProjectService();
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
}
