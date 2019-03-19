package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.service.ProjectService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class ProjectCreateCommand extends AbstractCommand {
    public ProjectCreateCommand(Bootstrap bootstrap) {
        super( bootstrap, true);
    }

    @Override
    public String getName() {
        return "cp";
    }

    @Override
    public String getDescription() {
        return "starts create new project dialog";
    }

    @Override
    public void execute(String ... params){
        DateFormat dateFormat = bootstrap.getDateFormat();
        ProjectService projectService = bootstrap.getProjectService();
        System.out.println("Enter project name");
        String projectName = sc.nextLine();
        System.out.println("Enter project description");
        String projectDescription = sc.nextLine();
        String date;
        Date projectStartDate;
        while(true) {
            System.out.println("Enter starting date (format DD.MM.YYYY) or leave empty for today");
            date = sc.nextLine();
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

        Date projectFinishDate;
        while (true) {
            System.out.println("Enter ending date (format DD.MM.YYYY)");
            date = sc.nextLine();
            try {
                projectFinishDate = dateFormat.parse(date);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        projectService.createProject(projectName, projectDescription, projectStartDate, projectFinishDate);

    }
}
