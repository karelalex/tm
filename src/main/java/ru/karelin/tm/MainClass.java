package ru.karelin.tm;

import ru.karelin.tm.service.ProjectService;

import static ru.karelin.tm.Statics.*;

import java.util.Scanner;


public class MainClass {

    private static String currentProjectId = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProjectDialog projectDialog = new ProjectDialog(sc);
        TaskDialog taskDialog = new TaskDialog(sc);
        ProjectService projectService = new ProjectService();
        String command;
        String[] commandParts;
        out:
        while (true) {
            if (!currentProjectId.isEmpty()) System.out.println("CurPrId: " + currentProjectId);
            System.out.print(">");
            command = sc.nextLine();
            commandParts = command.split(" ");

            switch (commandParts[0]) {
                case QUIT:
                    break out;
                case HELP:
                    showMainHelp();
                    break;
                case SET_CURRENT_PROJECT:
                    if (commandParts.length>1){
                        setCurrentProject(commandParts[1], projectService);
                    }
                    else setCurrentProject("", projectService);
                    break;
                case CREATE_PROJECT:
                    setCurrentProject(projectDialog.createProject(), projectService);
                    break;
                case EDIT_PROJECT:
                    if (commandParts.length > 1) {
                        projectDialog.editProject(commandParts[1]);
                    } else if (!currentProjectId.isEmpty()) {
                        projectDialog.editProject(currentProjectId);
                    }
                    break;
                case SHOW_PROJECT_LIST:
                    projectDialog.showProjectsList();
                    break;
                case SHOW_PROJECT:
                    if (commandParts.length > 1) {
                        projectDialog.showProject(commandParts[1]);
                    } else if (!currentProjectId.isEmpty()) {
                        projectDialog.showProject(currentProjectId);
                    }
                    break;
                case REMOVE_PROJECT:
                    if (commandParts.length > 1) {
                        projectDialog.removeProject(commandParts[1]);
                    } else if (!currentProjectId.isEmpty()) {
                        projectDialog.removeProject(currentProjectId);
                        currentProjectId = "";
                    }
                    break;
                case CREATE_TASK:
                    if (commandParts.length > 1)
                        taskDialog.createTask(commandParts[1]);
                    else if (!currentProjectId.isEmpty()) taskDialog.createTask(currentProjectId);
                    else taskDialog.createTask();
                    break;
                case SHOW_TASK:
                    if(commandParts.length>1){
                        taskDialog.showTask(commandParts[1]);
                    }
                case EDIT_TASK:
                    if (commandParts.length > 1) {
                        taskDialog.editTask(commandParts[1]);
                    }
                    break;
                case SHOW_TASK_LIST:
                    if (commandParts.length > 1) {
                        taskDialog.showTaskList(commandParts[1]);
                    } else if (!currentProjectId.isEmpty()) taskDialog.showTaskList(currentProjectId);
                    else taskDialog.showTaskList("");
                    break;
                case REMOVE_TASK:
                    if (commandParts.length > 1) {
                        taskDialog.removeTask(commandParts[1]);
                    }
                    break;

            }
        }
    }


    private static void showMainHelp() {
        System.out.println("Commmands: ");
        System.out.println("'" + SHOW_PROJECT_LIST + "' shows list of projects");
        System.out.println("'" + CREATE_PROJECT + "' starts create new project dialog");
        System.out.println("'" + REMOVE_PROJECT + " %number%' removes project with specified number and all its tasks");
        System.out.println("'" + SET_CURRENT_PROJECT +  " %number%' set current project to one with %number% ID");
        System.out.println("'" + SHOW_PROJECT +  " %number%' shows project info. If current project is set %number% may be skipped");
        System.out.println("'" + EDIT_PROJECT + " %number%' starts edit project dialog for specified project. If current project is set %number% may be skipped");
        System.out.println("'" + CREATE_TASK + " %number%' starts create new task dialog. Task will be created inside current or specified project.");
        System.out.println("'" + SHOW_TASK_LIST + " %number%' shows task list for current or specified project. If not show all tasks");
        System.out.println("'" + SHOW_TASK + " %number%' shows task with specified ID.");
        System.out.println("'" + EDIT_TASK + " %number%' starts edit task dialog for specified task.");
        System.out.println("'" + REMOVE_TASK +  " %number%' removes task with specified number");
        System.out.println("'" + QUIT + "' closes program");
        System.out.println("' "+ HELP + "' shows this help");
    }

    private static void setCurrentProject(String projectId, ProjectService pm) {
        if (projectId.isEmpty()) {
            currentProjectId = "";
            return;
        }
        if (pm.checkID(projectId)) currentProjectId = projectId;
    }
}
