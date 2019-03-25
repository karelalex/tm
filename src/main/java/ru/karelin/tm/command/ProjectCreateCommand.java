package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.api.service.ProjectService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public final class ProjectCreateCommand extends AbstractCommand {
    private static final boolean SECURED = true;
    public ProjectCreateCommand(@NotNull final ServiceLocator locator) {
        super( locator, SECURED);
    }
    public ProjectCreateCommand(){super(SECURED);}
    @Override
    public String getName() {
        return "cp";
    }

    @Override
    public String getDescription() {
        return "starts create new project dialog";
    }

    @Override
    public void execute(final String ... params){
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @NotNull final ProjectService projectService = locator.getProjectService();
        System.out.println("Enter project name");
        @NotNull final String projectName = ts.readLn();
        System.out.println("Enter project description");
        final String projectDescription = ts.readLn();
        @NotNull String date;
        @NotNull Date projectStartDate;
        while(true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or leave empty for today");
            date = ts.readLn();
            if (date.isEmpty()) {
                projectStartDate = new Date();
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

        @NotNull Date projectFinishDate;
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY)");
            date = ts.readLn();
            try {
                projectFinishDate = dateFormat.parse(date);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        projectService.create(locator.getCurrentUser().getId(), projectName, projectDescription, projectStartDate, projectFinishDate);

    }
}
