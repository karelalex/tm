package ru.karelin.tm.command;

import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.api.service.TaskService;
import ru.karelin.tm.entity.Task;

import java.text.DateFormat;


public final class TaskShowCommand extends AbstractCommand {

    private static final boolean SECURED = true;

    public TaskShowCommand(final ServiceLocator locator) {
        super(locator, SECURED);
    }
    public TaskShowCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "st";
    }

    @Override
    public String getDescription() {
        return "shows task with specified ID";
    }

    @Override
    public void execute(final String... params) {
        final TaskService taskService = locator.getTaskService();
        final String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        final DateFormat dateFormat = locator.getDateFormat();
        final String currentUserId = locator.getCurrentUser().getId();
        if (!taskService.checkID(currentUserId, taskId)) {
            System.out.println("Wrong ID");
            return;
        }
        final Task task = taskService.getOne(currentUserId, taskId);
        System.out.println("Task name: " + task.getName());
        System.out.println("Task description: " + task.getDescription());
        System.out.println("Task start date: " + dateFormat.format(task.getStartDate()));
        System.out.println("Task finish date " + dateFormat.format(task.getFinishDate()));
        System.out.println("Project ID: " + task.getProjectId());
        System.out.println();
    }
}
