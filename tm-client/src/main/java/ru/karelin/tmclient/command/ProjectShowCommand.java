package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.ProjectDto;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.SessionDto;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.DateFormat;

@ApplicationScoped
public class ProjectShowCommand extends AbstractCommand {

    @Inject
    private ServiceLocator locator;

    @Inject
    private DateConverter dateConverter;

    @Inject
    private ProjectEndpoint projectEndpoint;

    private static final boolean SECURED = true;

    public ProjectShowCommand() {
        super(SECURED);
    }

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
        @Nullable final DateFormat dateFormat = locator.getDateFormat();
        @Nullable final SessionDto session = locator.getCurrentSession();
        if (!projectEndpoint.checkProjectId(session, projectId)) {
            System.out.println("Wrong ID " + projectId);
            return;
        }
        @NotNull final ProjectDto project = projectEndpoint.getProject(session, projectId);
        if (project == null) System.out.println("No projects with");
        System.out.println("Project name: " + project.getName());
        System.out.println("Project description: " + project.getDescription());
        System.out.println("Creation date: " + dateFormat.format(dateConverter.convert(project.getCreationDate())));
        System.out.println("Start Date: " + dateFormat.format(dateConverter.convert(project.getStartDate())));
        System.out.println("End Date: " + dateFormat.format(dateConverter.convert(project.getFinishDate())));
        System.out.println("Status: " + project.getStatus().toString());
        System.out.println();
    }
}
