package ru.karelin.tm.commands;

import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.service.ProjectService;

public final class ProjectRemoveCommand extends AbstractCommand {
    public ProjectRemoveCommand(final ServiceLocator locator) {
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
    public void execute(final String... params) {
        String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        final ProjectService projectService = locator.getProjectService();
        projectService.remove(locator.getCurrentUser().getId(), projectId);
    }
}