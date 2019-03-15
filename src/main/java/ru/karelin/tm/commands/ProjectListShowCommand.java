package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.service.ProjectService;

import java.text.DateFormat;
import java.util.List;

public class ProjectListShowCommand extends AbstractCommand {
    public ProjectListShowCommand(String name, String description, Bootstrap bootstrap) {
        super("pl", "shows list of projects", bootstrap);
    }

    @Override
    public void execute(String... params) {
        ProjectService projectService = bootstrap.getProjectService();
        DateFormat dateFormat = bootstrap.getDateFormat();
        List<Project> projects = projectService.getProjectsList();
        for (Project p :projects) {
            System.out.println("Project ID: " + p.getId());
            System.out.println("Project name: " + p.getName() );
            System.out.println("Project finish date: " + dateFormat.format(p.getFinishDate()));
            System.out.println();
        }
    }
}
