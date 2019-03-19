package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.service.ProjectService;

import java.text.DateFormat;
import java.util.List;

public class ProjectListShowCommand extends AbstractCommand {
    public ProjectListShowCommand(Bootstrap bootstrap) {
        super(bootstrap, true);
    }

    @Override
    public String getName() {
        return "pl";
    }

    @Override
    public String getDescription() {
        return "shows list of projects";
    }

    @Override
    public void execute(String... params) {
        ProjectService projectService = bootstrap.getProjectService();
        DateFormat dateFormat = bootstrap.getDateFormat();
        List<Project> projects = projectService.getProjectsList(bootstrap.getCurrentUser().getId());
        for (Project p :projects) {
            System.out.println("Project ID: " + p.getId());
            System.out.println("Project name: " + p.getName() );
            System.out.println("Project finish date: " + dateFormat.format(p.getFinishDate()));
            System.out.println();
        }
    }
}
