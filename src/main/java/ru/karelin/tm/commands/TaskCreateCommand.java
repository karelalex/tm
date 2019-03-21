package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public final class TaskCreateCommand  extends AbstractCommand {
    public TaskCreateCommand(final ServiceLocator locator) {
        super(locator, true);
    }

    @Override
    public String getName() {
        return "ct";
    }

    @Override
    public String getDescription() {
        return "starts create new task dialog. Task will be created inside " +
                "specified project or project ID will be asked later.";
    }

    @Override
    public void execute(final String... params) {
        final DateFormat dateFormat = locator.getDateFormat();
        final ProjectService projectService = locator.getProjectService();
        final TaskService taskService = locator.getTaskService();
        final String currentUserId = locator.getCurrentUser().getId();
        String projectId="";
        if (params.length>0) projectId=params[0];
        System.out.println("Enter task name");
        final String taskName = sc.nextLine();
        System.out.println("Enter task description");
        final String taskDescription = sc.nextLine();
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
        while (!projectId.isEmpty() && !projectService.checkID(currentUserId, projectId)) {
            System.out.println("Wrong project id try again or leave it empty");
            projectId = sc.nextLine();
        }

        taskService.create(currentUserId, taskName, taskDescription, taskStartDate, taskFinishDate, projectId);

    }
}
