package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.service.TaskService;

import java.text.DateFormat;

public class TaskShowCommand extends AbstractCommand {
    public TaskShowCommand(ServiceLocator locator) {
        super(locator, true);
    }

    @Override
    public String getName() {
        return "st";
    }

    @Override
    public String getDescription() {
        return "shows task with specified ID";
    }

    @Override
    public void execute(String... params) {
        TaskService taskService = locator.getTaskService();
        String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        DateFormat dateFormat = locator.getDateFormat();
        String currentUserId = locator.getCurrentUser().getId();
        if (!taskService.checkID(currentUserId, taskId)) {
            System.out.println("Wrong ID");
            return;
        }
        Task task = taskService.getTask(currentUserId, taskId);
        System.out.println("Task name: " + task.getName());
        System.out.println("Task description: " + task.getDescription());
        System.out.println("Task start date: " + dateFormat.format(task.getStartDate()));
        System.out.println("Task finish date " + dateFormat.format(task.getFinishDate()));
        System.out.println("Project ID: " + task.getProjectID());
        System.out.println();
    }
}
