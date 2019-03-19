package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.service.ProjectService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class ProjectEditCommand extends AbstractCommand {
    public ProjectEditCommand(Bootstrap bootstrap) {
        super(bootstrap, true);
    }

    @Override
    public String getName() {
        return "ep";
    }

    @Override
    public String getDescription() {
        return "starts edit project dialog for specified project";
    }

    @Override
    public void execute(String... params) {
        ProjectService projectService = bootstrap.getProjectService();
        User currentUser = bootstrap.getCurrentUser();
        String projectId = params[0];
        DateFormat dateFormat = bootstrap.getDateFormat();
        if (!projectService.checkID(currentUser, projectId)) {
            System.out.println("Wrong ID " + projectId);
            return;
        }
        System.out.println("Enter new project name or just press enter if you do not want to change it");
        String projectName = sc.nextLine();
        System.out.println("Enter new project description or just press enter if you do not want to change it");
        String projectDescription = sc.nextLine();
        String date;
        Date projectStartDate;
        while (true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or just press enter if you do not want to change it");
            date = sc.nextLine();
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
            date = sc.nextLine();
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
        projectService.editProject(currentUser, projectId, projectName, projectDescription, projectStartDate, projectFinishDate);
    }
}


