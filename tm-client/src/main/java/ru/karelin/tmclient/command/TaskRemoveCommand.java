package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.TaskEndpoint;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TaskRemoveCommand extends AbstractCommand {

    @Inject
    private TaskEndpoint taskEndpoint;

    @Inject
    private ServiceLocator locator;

    private static final boolean SECURED = true;

    public TaskRemoveCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "rt";
    }

    @Override
    public String getDescription() {
        return "removes task with specified ID";
    }

    @Override
    public void execute(final String... params) throws WrongSessionException_Exception {
        final String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        taskEndpoint.removeTask(locator.getCurrentSession(), taskId);
    }
}
