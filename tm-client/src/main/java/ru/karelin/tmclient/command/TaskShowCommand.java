package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.*;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.DateFormat;

@ApplicationScoped
public class TaskShowCommand extends AbstractCommand {

    @Inject
    DateConverter dateConverter;

    @Inject
    ServiceLocator locator;

    @Inject
    TaskEndpoint taskEndpoint;


    private static final boolean SECURED = true;

    public TaskShowCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "st";
    }

    @Override
    public String getDescription() {
        return "shows task with specified ID";
    }

    @Override
    public void execute(final String... params) throws WrongSessionException_Exception {
        @NotNull final String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @Nullable final SessionDto session = locator.getCurrentSession();
        if (!taskEndpoint.checkTaskId(session, taskId)) {
            System.out.println("Wrong ID");
            return;
        }
        @NotNull final TaskDto task = taskEndpoint.getTask(session, taskId);
        System.out.println("Task name: " + task.getName());
        System.out.println("Task description: " + task.getDescription());
        System.out.println("Creation date: " + dateFormat.format(dateConverter.convert(task.getCreationDate())));
        System.out.println("Task start date: " + dateFormat.format(dateConverter.convert(task.getStartDate())));
        System.out.println("Task finish date " + dateFormat.format(dateConverter.convert(task.getFinishDate())));
        System.out.println("Status: " + task.getStatus().toString());
        System.out.println("Project ID: " + task.getProjectId());
        System.out.println();
    }
}
