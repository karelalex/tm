package ru.karelin.tm;

import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ProjectDialog {
    ProjectService projectService = new ProjectService();
    TaskService taskService = new TaskService();
    private Scanner sc;
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public ProjectDialog(Scanner sc) {
        this.sc = sc;
    }

    public String createProject(){
        System.out.println("Enter project name");
        String projectName = sc.nextLine();
        System.out.println("Enter project decription");
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
        return projectService.createProject(projectName, projectDescription, projectStartDate, projectFinishDate);

    }

    public void editProject(String projectId) {
        if(!projectService.checkID(projectId)) {
            System.out.println("Wrong ID "+ projectId);
            return;
        }
        System.out.println("Enter new project name or just press enter if you do not want to change it");
        String projectName = sc.nextLine();
        System.out.println("Enter new project description or just press enter if you do not want to change it");
        String projectDescription = sc.nextLine();
        String date;
        Date projectStartDate;
        while(true) {
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
        while(true) {
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
        projectService.editProject(projectId, projectName, projectDescription, projectStartDate, projectFinishDate);
    }

    public void showProjectsList() {
        projectService.showProjectsList();
    }

    public void removeProject(String projectId) {
        taskService.removeTasksByProjectID(projectId);
        projectService.removeProject(projectId);
    }

    public void showProject(String projectId) {
        if(!projectService.checkID(projectId)) {
            System.out.println("Wrong ID "+ projectId);
            return;
        }
        projectService.showProject(projectId);
    }
}
