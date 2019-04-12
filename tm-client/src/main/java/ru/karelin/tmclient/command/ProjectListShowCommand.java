package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.*;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.DateFormat;
import java.util.List;

@ApplicationScoped
public class ProjectListShowCommand extends AbstractCommand {

    @Inject
    private ProjectEndpoint projectEndpoint;

    @Inject
    private ServiceLocator locator;

    @Inject
    private DateConverter dateConverter;

    private static final boolean SECURED = true;

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
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @NotNull final SessionDto session = locator.getCurrentSession();
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

        @NotNull final List<ProjectDto> projects;
        if (isFind) projects = projectEndpoint.getProjectListByKeyword(session, searchItem);
        else if (isSorted) projects= projectEndpoint.getProjectSortedList(session, sortField, isStraight);
        else projects = projectEndpoint.getProjectList(session);
        for (ProjectDto p :projects) {
            System.out.println("Project ID: " + p.getId());
            System.out.println("Project name: " + p.getName() );
            System.out.println("Project description: " + p.getDescription());
            System.out.println("Creation date: " + dateFormat.format(dateConverter.convert(p.getCreationDate())));
            System.out.println("Start Date: " + dateFormat.format(dateConverter.convert(p.getStartDate())));
            System.out.println("End Date: " + dateFormat.format(dateConverter.convert(p.getFinishDate())));
            System.out.println("Status: " + p.getStatus().toString());
            System.out.println();
        }
    }
}
