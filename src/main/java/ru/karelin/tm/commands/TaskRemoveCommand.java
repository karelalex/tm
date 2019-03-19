package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.service.TaskService;

public class TaskRemoveCommand extends AbstractCommand {
    public TaskRemoveCommand(ServiceLocator locator) {
        super(locator, true);
    }

    @Override
    public String getName() {
        return "rt";
    }

    @Override
    public String getDescription() {
        return "removes task with specified ID";
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
        taskService.removeTask(locator.getCurrentUser().getId(), taskId);
    }
}
