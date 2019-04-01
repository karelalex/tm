package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.Session;
import ru.karelin.tmserver.endpoint.TaskEndpoint;


import javax.xml.datatype.DatatypeConfigurationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;


public final class TaskCreateCommand  extends AbstractCommand {
    private static final boolean SECURED = true;

    public TaskCreateCommand(@NotNull final ServiceLocator locator) {
        super(locator, SECURED);
    }
    public TaskCreateCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "ct";
    }

    @Override
    public String getDescription() {
        return "starts create new task dialog. Task will be created inside " +
                "specified project or project ID will be asked later.";
    }

    @Override
    public void execute(final String... params) throws DatatypeConfigurationException {
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @NotNull final ProjectEndpoint projectEndpoint = locator.getProjectEndpoint();
        @NotNull final TaskEndpoint taskEndpoint = locator.getTaskEndpoint();
        @Nullable final Session session = locator.getCurrentSession();
        @NotNull final DateConverter dateConverter = locator.getDateConverter();

        @NotNull String projectId="";
        if (params.length>0) projectId=params[0];
        System.out.println("Enter task name");
        @NotNull final String taskName = ts.readLn();
        System.out.println("Enter task description");
        @NotNull final String taskDescription = ts.readLn();
        @NotNull String date;
        @NotNull Date taskStartDate;
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or leave empty for today");
            date = ts.readLn();
            if (date.isEmpty()) {
                taskStartDate = new Date();
                break;
            } else {
                try {
                    taskStartDate = dateFormat.parse(date);
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        @NotNull Date taskFinishDate;
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY)");
            date = ts.readLn();
            try {
                taskFinishDate = dateFormat.parse(date);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (projectId.isEmpty()) {
            System.out.println("Enter project id where task will be added or leave it empty");
            projectId = ts.readLn();
        }
        while (!projectId.isEmpty() && !projectEndpoint.checkProjectId(session, projectId)) {
            System.out.println("Wrong project id try again or leave it empty");
            projectId = ts.readLn();
        }

        taskEndpoint.createTask(session, taskName, taskDescription, dateConverter.convert(taskStartDate), dateConverter.convert(taskFinishDate), projectId);

    }
}
