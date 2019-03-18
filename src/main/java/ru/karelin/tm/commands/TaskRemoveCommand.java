package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.service.TaskService;

public class TaskRemoveCommand extends AbstractCommand {
    public TaskRemoveCommand(Bootstrap bootstrap) {
        super("rt", "removes task with specified ID", bootstrap);
    }

    @Override
    public void execute(String... params) {
        TaskService taskService = bootstrap.getTaskService();
        String taskId = params[0];
        taskService.removeTask(taskId);
    }
}
