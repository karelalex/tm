package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;


public final class ProjectRemoveCommand extends AbstractCommand {
    private static final boolean SECURED = true;

    public ProjectRemoveCommand(@NotNull final ServiceLocator locator) {
        super( locator,SECURED);
    }
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
    public void execute(final String... params) {
        String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        @NotNull final ProjectEndpoint projectEndpoint = locator.getProjectEndpoint();
        projectEndpoint.removeProject(locator.getCurrentUser().getId(), projectId);
    }
}