package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.api.service.ProjectService;

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
    public void execute(final String... params) {

        @NotNull final ProjectService projectService = locator.getProjectService();
        @NotNull final DateFormat dateFormat = locator.getDateFormat();
        @NotNull final List<Project> projects = projectService.getList(locator.getCurrentUser().getId());
        for (Project p :projects) {
            System.out.println("Project ID: " + p.getId());
            System.out.println("Project name: " + p.getName() );
            System.out.println("Creation date: " + p.getCreationDate());
            System.out.println("Start Date: " + dateFormat.format(p.getStartDate()));
            System.out.println("End Date: " + dateFormat.format(p.getFinishDate()));
            System.out.println("Status: " + p.getStatus().toString());
            System.out.println();
        }
    }
}
