package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.service.TaskService;

public final class TaskRemoveCommand extends AbstractCommand {
    public TaskRemoveCommand(final ServiceLocator locator) {
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
    public void execute(final String... params) {
        final TaskService taskService = locator.getTaskService();
        final String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        taskService.remove(locator.getCurrentUser().getId(), taskId);
    }
}
