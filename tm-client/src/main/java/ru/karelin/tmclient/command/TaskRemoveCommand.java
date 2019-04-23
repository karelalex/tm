package ru.karelin.tmclient.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.TaskEndpoint;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

@Component
public class TaskRemoveCommand extends AbstractCommand {

    @Autowired
    private TaskEndpoint taskEndpoint;

    @Autowired
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
