package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.service.ProjectService;

public class ProjectRemoveCommand extends AbstractCommand {
    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super( bootstrap,true);
    }

    @Override
    public String getName() {
        return "rp";
    }

    @Override
    public String getDescription() {
        return "removes project with specified number and all its tasks";
    }

    @Override
    public void execute(String... params) {
        ProjectService projectService = bootstrap.getProjectService();
        String projectId = params[0];
        projectService.removeProject(projectId);
    }
}