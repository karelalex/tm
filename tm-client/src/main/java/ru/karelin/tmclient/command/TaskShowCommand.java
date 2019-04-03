package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.Session;
import ru.karelin.tmserver.endpoint.Task;
import ru.karelin.tmserver.endpoint.TaskEndpoint;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

import java.text.DateFormat;


public final class TaskShowCommand extends AbstractCommand {

    private static final boolean SECURED = true;

    public TaskShowCommand(final ServiceLocator locator) {
        super(locator, SECURED);
    }
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
        @NotNull final TaskEndpoint taskEndpoint = locator.getTaskEndpoint();
        @NotNull final String taskId;
        if (params.length > 0) taskId = params[0];
        else {
            System.out.println("You must enter taskId");
            return;
        }
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @NotNull final DateConverter dateConverter = locator.getDateConverter();
        @Nullable final Session session = locator.getCurrentSession();
        if (!taskEndpoint.checkTaskId(session, taskId)) {
            System.out.println("Wrong ID");
            return;
        }
        @NotNull final Task task = taskEndpoint.getTask(session, taskId);
        System.out.println("Task name: " + task.getName());
        System.out.println("Task description: " + task.getDescription());
        System.out.println("Creation date: " + dateFormat.format(dateConverter.convert(task.getCreationDate())));
        System.out.println("Task start date: " + dateFormat.format(dateConverter.convert(task.getStartDate())));
        System.out.println("Task finish date " + dateFormat.format(dateConverter.convert(task.getFinishDate())));
        System.out.println("Status: " + task.getStatus().toString());
        System.out.println("Project ID: " + task.getProjectID());
        System.out.println();
    }
}
