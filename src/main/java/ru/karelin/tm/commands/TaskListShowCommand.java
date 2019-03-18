package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;

import java.util.List;

public class TaskListShowCommand extends AbstractCommand {
    public TaskListShowCommand(Bootstrap bootstrap) {
        super("tl", "shows task list for specified project. If not shows all tasks", bootstrap);
    }

    @Override
    public void execute(String... params) {
        String projectId = params[0];
        TaskService taskService = bootstrap.getTaskService();
        ProjectService projectService = bootstrap.getProjectService();
        List<Task> tasks;
        boolean showProjectId=true;
        if (projectId.isEmpty()) tasks=taskService.getTaskList();
        else if(projectService.checkID(projectId)) {
            tasks=taskService.getTaskList(projectId);
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
