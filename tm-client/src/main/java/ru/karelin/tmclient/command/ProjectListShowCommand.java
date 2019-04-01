package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.Project;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.Session;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

import java.text.DateFormat;
import java.util.List;


public final class ProjectListShowCommand extends AbstractCommand {
    private static final boolean SECURED = true;

    public ProjectListShowCommand(@NotNull final ServiceLocator locator) {
        super(locator, SECURED);
    }
    public ProjectListShowCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "pl";
    }

    @Override
    public String getDescription() {
        return "shows list of projects";
    }

    @Override
    public void execute(final String... params) throws WrongSessionException_Exception {

        @NotNull final ProjectEndpoint projectEndpoint = locator.getProjectEndpoint();
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @NotNull final DateConverter dateConverter = locator.getDateConverter();
        @NotNull final Session session = locator.getCurrentSession();
        boolean isSorted = false;
        @NotNull String sortField = "";
        boolean isStraight = true;
        boolean isFind = false;
        String searchItem="";
        if (params.length > 0) {
            int iter=0;
            while (iter < params.length) {
                if (params[iter].equals("-sort")) {
                    isSorted = true;
                    iter++;
                    if (iter < params.length && !params[iter].startsWith("-")) {
                        sortField = params[iter];
                        iter++;
                        if (iter < params.length && !params[iter].startsWith("-")) {
                            if (params[iter].startsWith("desc")) isStraight = false;
                            iter++;
                        }
                    }
                }
                if(iter<params.length && params[iter].equals("-search")){
                    isFind = true;
                    iter ++;
                    if(iter<params.length && !params[iter].startsWith("-")){
                        searchItem = params[iter];
                    }
                }
                iter++;

            }

        }

        @NotNull final List<Project> projects;
        if (isFind) projects = projectEndpoint.getProjectListByKeyword(session, searchItem);
        else if (isSorted) projects= projectEndpoint.getProjectSortedList(session, sortField, true);
        else projects = projectEndpoint.getProjectList(session);
        for (Project p :projects) {
            System.out.println("Project ID: " + p.getId());
            System.out.println("Project name: " + p.getName() );
            System.out.println("Creation date: " + p.getCreationDate());
            System.out.println("Start Date: " + dateFormat.format(dateConverter.convert(p.getStartDate())));
            System.out.println("End Date: " + dateFormat.format(dateConverter.convert(p.getFinishDate())));
            System.out.println("Status: " + p.getStatus().toString());
            System.out.println();
        }
    }
}
