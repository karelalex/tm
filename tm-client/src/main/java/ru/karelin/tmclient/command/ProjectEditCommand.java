package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.ProjectEndpoint;
import ru.karelin.tmserver.endpoint.SessionDto;
import ru.karelin.tmserver.endpoint.Status;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;


import javax.xml.datatype.DatatypeConfigurationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;


public final class ProjectEditCommand extends AbstractCommand {
    private static final boolean SECURED = true;

    public ProjectEditCommand(@NotNull final ServiceLocator locator) {
        super(locator, SECURED);
    }
    public ProjectEditCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "ep";
    }

    @Override
    public String getDescription() {
        return "starts edit project dialog for specified project";
    }

    @Override
    public void execute(final String... params) throws DatatypeConfigurationException, WrongSessionException_Exception {
        @NotNull String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        final ProjectEndpoint projectEndpoint = locator.getProjectEndpoint();
        final SessionDto currentSession = locator.getCurrentSession();
        final DateFormat dateFormat = locator.getDateFormat();
        final DateConverter dateConverter = locator.getDateConverter();
        if (!projectEndpoint.checkProjectId(currentSession, projectId)) {
            System.out.println("Wrong ID " + projectId);
            return;
        }
        System.out.println("Enter new project name or just press enter if you do not want to change it");
        final String projectName = ts.readLn();
        System.out.println("Enter new project description or just press enter if you do not want to change it");
        final String projectDescription = ts.readLn();
        String projectStatusString;
        Status projectStatus;
        stat:
        while (true){
            System.out.println("Enter new project status or leave empty to keep current status.\nYou must enter one of the these values: ");
            for (Status s: Status.values()
            ) {
                System.out.print(s.toString()+", ");
            }
            System.out.println("\b\b");
            projectStatusString = ts.readLn();
            if(projectStatusString.isEmpty()) {
                projectStatus=null;
                break stat;
            }
            for (Status s : Status.values()) {
                if (s.toString().equals(projectStatusString)) {
                    projectStatus = s;
                    break stat;
                }
            }
        }
        String date;
        Date projectStartDate;
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or just press enter if you do not want to change it");
            date = ts.readLn();
            if (date.isEmpty()) {
                projectStartDate = null;
                break;
            } else {
                try {
                    projectStartDate = dateFormat.parse(date);
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        Date projectFinishDate;
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY) or just press enter if you do not want to change it");
            date = ts.readLn();
            if (date.isEmpty()) {
                projectFinishDate = null;
                break;
            } else {
                try {
                    projectFinishDate = dateFormat.parse(date);
                    break;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        projectEndpoint.editProject(currentSession,
                projectId,
                projectName,
                projectDescription,
                dateConverter.convert(projectStartDate),
                dateConverter.convert(projectFinishDate),
                projectStatus);
    }
}


