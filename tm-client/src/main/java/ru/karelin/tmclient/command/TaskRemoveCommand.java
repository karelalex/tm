package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.service.TaskService;
import ru.karelin.tm.api.util.ServiceLocator;


public final class TaskRemoveCommand extends AbstractCommand {

    private static final boolean SECURED = true;

    public TaskRemoveCommand(@NotNull final ServiceLocator locator) {
        super(locator, SECURED);
    }

    public TaskRemoveCommand(){super(SECURED);}

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
