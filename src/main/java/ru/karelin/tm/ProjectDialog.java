package ru.karelin.tm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ProjectDialog {
    ProjectManipulator projectManipulator = new ProjectManipulator();
    TaskManipulator taskManipulator = new TaskManipulator();
    private Scanner sc;
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public ProjectDialog(Scanner sc) {
        this.sc = sc;
    }

    public void createProject(){
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
        projectManipulator.createProject(projectName, projectDescription, projectStartDate, projectFinishDate);

    }

    public void editProject(String projectId) {
        if(!projectManipulator.checkID(projectId)) {
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
        projectManipulator.editProject(projectId, projectName, projectDescription, projectStartDate, projectFinishDate);
    }

    public void showProjectsList() {
        projectManipulator.showProjectsList();
    }

    public void removeProject(String projectId) {
        taskManipulator.removeTasksByProjectID(projectId);
        projectManipulator.removeProject(projectId);
    }

    public void showProject(String projectId) {
        if(!projectManipulator.checkID(projectId)) {
            System.out.println("Wrong ID "+ projectId);
            return;
        }
        projectManipulator.showProject(projectId);
    }
}
