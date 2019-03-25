package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.TaskService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;


public final class TaskEditCommand extends AbstractCommand {
    private static final boolean SECURED = true;

    public TaskEditCommand(@NotNull ServiceLocator locator) {
        super(locator, SECURED);
    }
    public TaskEditCommand(){super(SECURED);}

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
        @NotNull final String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        @NotNull final TaskService taskService = locator.getTaskService();
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @Nullable final String currentUserId = locator.getCurrentUser().getId();
        @NotNull final ProjectService projectService = locator.getProjectService();
        if (!taskService.checkID(currentUserId, taskId)) {
            System.out.println("Wrong ID!");
            return;
        }
        System.out.println("Enter new task name or just press enter if you do not want to change it");
        @NotNull final String taskName = ts.readLn();
        System.out.println("Enter new task description or just press enter if you do not want to change it");
        @NotNull final String taskDescription = ts.readLn();
        @NotNull String date;
        @Nullable Date taskStartDate;
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or just press enter if you do not want to change it");
            date = ts.readLn();
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
        @Nullable Date taskFinishDate;
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY) or just press enter if you do not want to change it");
            date = ts.readLn();
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
        @NotNull String projectId = ts.readLn();
        while (!projectId.isEmpty() && !projectService.checkID(currentUserId, projectId)) {
            System.out.println("Wrong project id try again or leave it empty");
            projectId = ts.readLn();
        }

        taskService.edit(currentUserId, taskId, taskName, taskDescription, taskStartDate, taskFinishDate, projectId);

    }
}
