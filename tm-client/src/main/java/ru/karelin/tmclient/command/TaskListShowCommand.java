package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.*;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.DateFormat;
import java.util.List;

@ApplicationScoped
public class TaskListShowCommand extends AbstractCommand {

    @Inject
    private ServiceLocator locator;

    @Inject
    private DateConverter dateConverter;

    @Inject
    private TaskEndpoint taskEndpoint;

    @Inject
    private ProjectEndpoint projectEndpoint;

    private static final boolean SECURED = true;

    public TaskListShowCommand() {
        super(SECURED);
    }

    @Override
    public String getName() {
        return "tl";
    }

    @Override
    public String getDescription() {
        return "shows task list for specified project. If not shows all tasks";
    }

    @Override
    public void execute(final String... params) throws WrongSessionException_Exception {
        @Nullable final SessionDto session = locator.getCurrentSession();
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @NotNull final String projectId;
        boolean isSorted = false;
        String sortField = "";
        boolean isStraight = true;
        String searchItem = "";
        boolean isFind = false;
        if (params.length > 0) {
            int iter;
            if (!params[0].startsWith("-")) {
                projectId = params[0];
                iter = 1;
            } else {
                iter = 0;
                projectId = "";
            }
            while (iter < params.length) {
                if (params[iter].equals("-sort")) {
                    isSorted = true;
                    iter++;
                    if (iter < params.length && !params[iter].startsWith("-")) {
                        sortField = params[iter];
                        iter++;
                        if (iter < params.length && !params[iter].startsWith("-")) {
                            if (params[iter].startsWith("desc")) isStraight = false;

                        }
                    }
                    if (iter < params.length && params[iter].equals("-search")) {
                        isFind = true;
                        iter++;
                        if (iter < params.length && !params[iter].startsWith("-")) {
                            searchItem = params[iter];
                        }
                    }
                    iter++;
                }
                iter++;
            }

        } else projectId = "";
        @NotNull final List<TaskDto> tasks;
        boolean showProjectId = true;
        if (isFind) tasks = taskEndpoint.getTaskListByKeyword(session, searchItem);
        else if (projectId.isEmpty()) {
            if (isSorted) {
                tasks = taskEndpoint.getSortedTaskList(session, sortField, isStraight);
            } else tasks = taskEndpoint.getTaskList(session);
        } else if (projectEndpoint.checkProjectId(session, projectId)) {
            if (isSorted) {
                tasks = taskEndpoint.getSortedTaskListByProjectId(session, projectId, sortField, isStraight);
            } else tasks = taskEndpoint.getTaskListByProjectId(session, projectId);
            showProjectId = false;
        } else {
            System.out.println("Wrong Project ID");
            return;
        }
        for (TaskDto t : tasks) {
            System.out.println("Task: " + t.getId());
            System.out.println("Task name: " + t.getName());
            System.out.println("Task description: " + t.getDescription());
            System.out.println("Creation date: " + dateFormat.format(dateConverter.convert(t.getCreationDate())));
            System.out.println("Task start date: " + dateFormat.format(dateConverter.convert(t.getStartDate())));
            System.out.println("Task finish date " + dateFormat.format(dateConverter.convert(t.getFinishDate())));
            System.out.println("Status: " + t.getStatus().toString());
            if (showProjectId) System.out.println("Project ID: " + t.getProjectId());
            System.out.println();
        }
    }
}
