package ru.karelin.tm.command;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.TaskService;

import java.text.DateFormat;
import java.util.List;


public final class TaskListShowCommand extends AbstractCommand {
    private static final boolean SECURED = true;

    public TaskListShowCommand(@NotNull final ServiceLocator locator) {
        super(locator, SECURED);
    }

    public TaskListShowCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "tl";
    }

    @Override
    public String getDescription() {
        return "shows task list for specified project. If not shows all tasks";
    }

    @Override
    public void execute(final String... params) {
        @Nullable final String currentUserId = locator.getCurrentUser().getId();
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @NotNull final String projectId;
        boolean isSorted = false;
        String sortField = "";
        boolean isStraight = true;
        if (params.length > 0) {
            int iter;
            if (!params[0].startsWith("-")) {
                projectId = params[0];
                iter = 1;
            } else {
                iter = 0;
                projectId = "";
            }
            while (iter < params.length) {
                if (params[iter].equals("-sort")) {
                    isSorted = true;
                    iter++;
                    if (iter < params.length && !params[iter].startsWith("-")) {
                        sortField = params[iter];
                        iter++;
                        if (iter < params.length && !params[iter].startsWith("-")) {
                            if (params[iter].startsWith("desc")) isStraight = false;

                        }
                    }
                }
                iter++;
            }

        } else projectId = "";
        @NotNull final TaskService taskService = locator.getTaskService();
        @NotNull final ProjectService projectService = locator.getProjectService();
        @NotNull final List<Task> tasks;
        boolean showProjectId = true;
        if (projectId.isEmpty()) {
            if (isSorted) {
                tasks = taskService.getSortedList(currentUserId, sortField, isStraight);
            } else tasks = taskService.getList(currentUserId);
        } else if (projectService.checkID(currentUserId, projectId)) {
            if(isSorted){
                tasks = taskService.getSortedListByProjectId(currentUserId, projectId, sortField, isStraight);
            }
            else tasks = taskService.getListByProjectId(currentUserId, projectId);
            showProjectId = false;
        } else {
            System.out.println("Wrong Project ID");
            return;
        }
        for (
                Task t : tasks) {
            System.out.println("Task: " + t.getId());
            System.out.println("Task name: " + t.getName());
            System.out.println("Task description: " + t.getDescription());
            System.out.println("Creation date: " + t.getCreationDate());
            System.out.println("Task start date: " + dateFormat.format(t.getStartDate()));
            System.out.println("Task finish date " + dateFormat.format(t.getFinishDate()));
            System.out.println("Status: " + t.getStatus().toString());
            if (showProjectId) System.out.println("Project ID: " + t.getProjectID());
            System.out.println();
        }
    }
}
