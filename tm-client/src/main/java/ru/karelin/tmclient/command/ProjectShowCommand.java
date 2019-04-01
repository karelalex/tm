package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.Project;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.Session;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

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
    public void execute(final String... params) throws WrongSessionException_Exception {
        @Nullable final String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        @NotNull final ProjectEndpoint projectEndpoint = locator.getProjectEndpoint();
        @Nullable final DateFormat dateFormat = locator.getDateFormat();
        @NotNull final DateConverter dateConverter = locator.getDateConverter();
        @Nullable final Session session = locator.getCurrentSession();
        if(!projectEndpoint.checkProjectId(session, projectId)) {
            System.out.println("Wrong ID "+ projectId);
            return;
        }
        @NotNull final Project project = projectEndpoint.getProject(session, projectId);
        if (project == null) System.out.println("No projects with");
        System.out.println("Project name: " + project.getName() );
        System.out.println("Project description: " + project.getDescription());
        System.out.println("Creation date: " + project.getCreationDate());
        System.out.println("Start Date: " + dateFormat.format(dateConverter.convert(project.getStartDate())));
        System.out.println("End Date: " + dateFormat.format(dateConverter.convert(project.getFinishDate())));
        System.out.println("Status: " + project.getStatus().toString());
        System.out.println();
    }
}
