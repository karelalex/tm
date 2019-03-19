package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.service.ProjectService;

import java.text.DateFormat;

public class ProjectShowCommand extends AbstractCommand{
    public ProjectShowCommand(Bootstrap bootstrap) {
        super(bootstrap, true);
    }

    @Override
    public String getName() {
        return "sp";
    }

    @Override
    public String getDescription() {
        return "shows project info";
    }

    @Override
    public void execute(String... params) {
        String projectId;
        if (params.length > 0) projectId = params[0];
        else {
            System.out.println("You must enter projectId");
            return;
        }
        ProjectService projectService = locator.getProjectService();
        DateFormat dateFormat = locator.getDateFormat();
        String currentUserId = locator.getCurrentUser().getId();
        if(!projectService.checkID(currentUserId,projectId)) {
            System.out.println("Wrong ID "+ projectId);
            return;
        }
        Project project = projectService.getProject(currentUserId, projectId);
        System.out.println("Project name: " + project.getName() );
        System.out.println("Project name: " + project.getDescription());
        System.out.println("Start Date: " + dateFormat.format(project.getStartDate()));
        System.out.println("End Date: " + dateFormat.format(project.getFinishDate()));
        System.out.println();
    }
}
