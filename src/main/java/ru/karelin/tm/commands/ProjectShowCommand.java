package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.service.ProjectService;

import java.text.DateFormat;

public class ProjectShowCommand extends AbstractCommand{
    public ProjectShowCommand(String name, String description, Bootstrap bootstrap) {
        super("sp",  "shows project info", bootstrap);
    }

    @Override
    public void execute(String... params) {
        ProjectService projectService = bootstrap.getProjectService();
        String projectId = params[0];
        DateFormat dateFormat = bootstrap.getDateFormat();
        if(!projectService.checkID(projectId)) {
            System.out.println("Wrong ID "+ projectId);
            return;
        }
        Project project = projectService.getProject(projectId);
        System.out.println("Project name: " + project.getName() );
        System.out.println("Project name: " + project.getDescription());
        System.out.println("Start Date: " + dateFormat.format(project.getStartDate()));
        System.out.println("End Date: " + dateFormat.format(project.getFinishDate()));
        System.out.println();
    }
}
