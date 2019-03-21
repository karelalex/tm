package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public final class TaskEditCommand extends AbstractCommand {
    public TaskEditCommand(ServiceLocator locator) {
        super(locator, true);
    }

    @Override
    public String getName() {
        return "et";
    }

    @Override
    public String getDescription() {
        return "starts edit task dialog for specified task";
    }

    @Override
    public void execute(final String... params) {
        final String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        final TaskService taskService = locator.getTaskService();
        final DateFormat dateFormat = locator.getDateFormat();
        final String currentUserId = locator.getCurrentUser().getId();
        final ProjectService projectService = locator.getProjectService();
        if (!taskService.checkID(currentUserId, taskId)) {
            System.out.println("Wrong ID!");
            return;
        }
        System.out.println("Enter new task name or just press enter if you do not want to change it");
        final String taskName = sc.nextLine();
        System.out.println("Enter new task description or just press enter if you do not want to change it");
        final String taskDescription = sc.nextLine();
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
        while (!projectId.isEmpty() && !projectService.checkID(currentUserId, projectId)) {
            System.out.println("Wrong project id try again or leave it empty");
            projectId = sc.nextLine();
        }

        taskService.edit(currentUserId, taskId, taskName, taskDescription, taskStartDate, taskFinishDate, projectId);

    }
}
