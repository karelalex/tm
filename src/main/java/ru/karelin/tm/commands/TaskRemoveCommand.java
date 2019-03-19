package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.service.TaskService;

public class TaskRemoveCommand extends AbstractCommand {
    public TaskRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap, true);
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
        TaskService taskService = bootstrap.getTaskService();
        String taskId = params[0];
        taskService.removeTask(taskId);
    }
}
