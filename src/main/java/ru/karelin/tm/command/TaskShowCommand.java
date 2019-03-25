package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
        @NotNull final TaskService taskService = locator.getTaskService();
        @NotNull final String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @Nullable final String currentUserId = locator.getCurrentUser().getId();
        if (!taskService.checkID(currentUserId, taskId)) {
            System.out.println("Wrong ID");
            return;
        }
        @NotNull final Task task = taskService.getOne(currentUserId, taskId);
        System.out.println("Task name: " + task.getName());
        System.out.println("Task description: " + task.getDescription());
        System.out.println("Task start date: " + dateFormat.format(task.getStartDate()));
        System.out.println("Task finish date " + dateFormat.format(task.getFinishDate()));
        System.out.println("Project ID: " + task.getProjectID());
        System.out.println();
    }
}
