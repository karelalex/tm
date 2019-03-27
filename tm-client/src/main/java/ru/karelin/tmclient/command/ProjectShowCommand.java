package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.entity.Project;

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
        @Nullable final String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        @NotNull final ProjectService projectService = locator.getProjectService();
        @Nullable final DateFormat dateFormat = locator.getDateFormat();
        @Nullable final String currentUserId = locator.getCurrentUser().getId();
        if(!projectService.checkID(currentUserId, projectId)) {
            System.out.println("Wrong ID "+ projectId);
            return;
        }
        @NotNull final Project project = projectService.getOne(currentUserId, projectId);
        System.out.println("Project name: " + project.getName() );
        System.out.println("Project description: " + project.getDescription());
        System.out.println("Creation date: " + project.getCreationDate());
        System.out.println("Start Date: " + dateFormat.format(project.getStartDate()));
        System.out.println("End Date: " + dateFormat.format(project.getFinishDate()));
        System.out.println("Status: " + project.getStatus().toString());
        System.out.println();
    }
}
