package ru.karelin.tmclient.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;


@Component
public class ProjectRemoveCommand extends AbstractCommand {

    @Autowired
    private ProjectEndpoint projectEndpoint;

    @Autowired
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