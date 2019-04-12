package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProjectRemoveCommand extends AbstractCommand {

    @Inject
    private ProjectEndpoint projectEndpoint;

    @Inject
    private ServiceLocator locator;

    private static final boolean SECURED = true;

    public ProjectRemoveCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "rp";
    }

    @Override
    public String getDescription() {
        return "removes project with specified number and all its tasks";
    }

    @Override
    public void execute(final String... params) throws WrongSessionException_Exception {
        String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        projectEndpoint.removeProject(locator.getCurrentSession(), projectId);
    }
}