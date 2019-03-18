package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.service.ProjectService;

public class ProjectRemoveCommand extends AbstractCommand {
    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super("rp", "removes project with specified number and all its tasks", bootstrap);
    }

    @Override
    public void execute(String... params) {
        ProjectService projectService = bootstrap.getProjectService();
        String projectId = params[0];
        projectService.removeProject(projectId);
    }
}