package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.api.service.ProjectService;

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
    public void execute(final String... params) {
        @NotNull String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        final ProjectService projectService = locator.getProjectService();
        final String currentUserId = locator.getCurrentUser().getId();
        final DateFormat dateFormat = locator.getDateFormat();
        if (!projectService.checkID(currentUserId, projectId)) {
            System.out.println("Wrong ID " + projectId);
            return;
        }
        System.out.println("Enter new project name or just press enter if you do not want to change it");
        final String projectName = ts.readLn();
        System.out.println("Enter new project description or just press enter if you do not want to change it");
        final String projectDescription = ts.readLn();
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
        projectService.edit(currentUserId, projectId, projectName, projectDescription, projectStartDate, projectFinishDate);
    }
}


