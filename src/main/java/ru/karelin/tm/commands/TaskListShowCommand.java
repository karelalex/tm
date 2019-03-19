package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;

import java.util.List;

public class TaskListShowCommand extends AbstractCommand {
    public TaskListShowCommand(Bootstrap bootstrap) {
        super(bootstrap, true);
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
    public void execute(String... params) {
        String currentUserId = bootstrap.getCurrentUser().getId();
        String projectId = null;
        if (params.length>0) projectId=params[0];
        TaskService taskService = bootstrap.getTaskService();
        ProjectService projectService = bootstrap.getProjectService();
        List<Task> tasks;
        boolean showProjectId=true;
        if (projectId.isEmpty()) tasks=taskService.getTaskList(currentUserId);
        else if(projectService.checkID(currentUserId, projectId)) {
            tasks=taskService.getTaskList(currentUserId, projectId);
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
