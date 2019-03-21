package ru.karelin.tm.commands;

import ru.karelin.tm.api.ServiceLocator;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.TaskService;

import java.util.List;

public final class TaskListShowCommand extends AbstractCommand {
    public TaskListShowCommand(final ServiceLocator locator) {
        super(locator, true);
    }

    @Override
    public String getName() {
        return"tl";
    }

    @Override
    public String getDescription() {
        return "shows task list for specified project. If not shows all tasks";
    }

    @Override
    public void execute(final String... params) {
        final String currentUserId = locator.getCurrentUser().getId();
        final String projectId;
        if (params.length>0) projectId=params[0];
        else projectId="";
        final TaskService taskService = locator.getTaskService();
        final ProjectService projectService = locator.getProjectService();
        final List<Task> tasks;
        boolean showProjectId=true;
        if (projectId.isEmpty()) tasks=taskService.getList(currentUserId);
        else if(projectService.checkID(currentUserId, projectId)) {
            tasks=taskService.getListByProjectId(currentUserId, projectId);
            showProjectId=false;
        }
        else {
            System.out.println("Wrong Project ID");
            return;
        }
        for ( Task t : tasks) {
            System.out.println("Task: " + t.getId());
            System.out.println("Task name: " + t.getName());
            System.out.println("Task description: " + t.getDescription());
            if (showProjectId) System.out.println("Project ID: " + t.getProjectID());
            System.out.println();
        }
    }
}
