package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.service.ProjectService;

public class ProjectRemoveCommand extends AbstractCommand {
    public ProjectRemoveCommand(ServiceLocator locator) {
        super( locator,true);
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
        String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        ProjectService projectService = locator.getProjectService();
        projectService.removeProject(locator.getCurrentUser().getId(), projectId);
    }
}