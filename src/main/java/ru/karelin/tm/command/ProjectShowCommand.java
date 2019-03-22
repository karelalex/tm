package ru.karelin.tm.command;

import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.api.service.ProjectService;

import java.text.DateFormat;


public final class ProjectShowCommand extends AbstractCommand{
    private static final boolean SECURED = true;

    public ProjectShowCommand(final ServiceLocator locator) {
        super(locator, SECURED);
    }
    public ProjectShowCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "sp";
    }

    @Override
    public String getDescription() {
        return "shows project info";
    }

    @Override
    public void execute(final String... params) {
        final String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        final ProjectService projectService = locator.getProjectService();
        final DateFormat dateFormat = locator.getDateFormat();
        final String currentUserId = locator.getCurrentUser().getId();
        if(!projectService.checkID(currentUserId,projectId)) {
            System.out.println("Wrong ID "+ projectId);
            return;
        }
        final Project project = projectService.getOne(currentUserId, projectId);
        System.out.println("Project name: " + project.getName() );
        System.out.println("Project description: " + project.getDescription());
        System.out.println("Start Date: " + dateFormat.format(project.getStartDate()));
        System.out.println("End Date: " + dateFormat.format(project.getFinishDate()));
        System.out.println();
    }
}
