package ru.karelin.tm;

import static ru.karelin.tm.Statics.*;

import java.util.Scanner;


public class MainClass {

    private static String currentProjectId="";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProjectDialog projectDialog = new ProjectDialog(sc);
        TaskDialog taskDialog = new TaskDialog(sc);
        ProjectManipulator projectManipulator = new ProjectManipulator();
        String currentProjectId="";
        String command;
        String[] commandParts;
        out:
        while (true) {
            if(!currentProjectId.isEmpty()) System.out.println(currentProjectId);
            System.out.print(">");
            command = sc.nextLine();
            commandParts = command.split(" ");

            switch (commandParts[0]) {
                case QUIT:
                    break out;
                case HELP:
                    showMainHelp();
                    break;
                case CREATE_PROJECT:
                    setCurrentProject(projectDialog.createProject(), projectManipulator);
                    break;
                case EDIT_PROJECT:
                    if (commandParts.length > 1) {
                        projectDialog.editProject(commandParts[1]);
                    }
                    else if(!currentProjectId.isEmpty()){
                        projectDialog.editProject(currentProjectId);
                    }
                case SHOW_PROJECT_LIST:
                    projectDialog.showProjectsList();
                    break;
                case SHOW_PROJECT:
                    if(commandParts.length>1) {
                        projectDialog.showProject(commandParts[1]);
                    }
                    else if (!currentProjectId.isEmpty()){
                        projectDialog.showProject(currentProjectId);
                    }
                    break;
                case REMOVE_PROJECT:
                    if (commandParts.length > 1) {
                        projectDialog.removeProject(commandParts[1]);
                    }
                    else if (!currentProjectId.isEmpty()){
                        projectDialog.removeProject(currentProjectId);
                    }
                    break;
                case CREATE_TASK:
                    taskDialog.createTask();
                    break;
                case EDIT_TASK:
                    if (commandParts.length > 1) {
                    taskDialog.editTask(commandParts[1]);
                    }
                    break;
                case SHOW_TASK_LIST:
                    if(commandParts.length>1){

                    }
                    break;
                case REMOVE_TASK:
                    if (commandParts.length > 1) {projectDialog.removeProject(commandParts[1]);
                    }

            }
        }
    }



   /* private static void showProjectHelp() {
        System.out.println("Commmands: ");
        System.out.println("'" + LEAVE_PROJECT + "' exit to main menu");
        System.out.println("'" + EDIT_PROJECT_DESCRIPTION + "' allows to change description of current project");
        System.out.println("'" + SHOW_PROJECT_TASK_LIST + "' shows task list of current project");
        System.out.println("'" + SHOW_PROJECT_TASK +  " %number%' shows task with specified number in current project ");
        System.out.println("'" + ADD_TASK_TO_PROJECT + "' adds task to current project");
        System.out.println("'" + EDIT_PROJECT_TASK +  " %number%' allows to edit task with specified number in current project ");
        System.out.println("'" + REMOVE_PROJECT_TASK +  " %number%' removes task with specified number from current project ");
        System.out.println("'" + REMOVE_PROJECT + "' removes current project");
    }*/

    private static void showMainHelp(){
        System.out.println("Commmands: ");
        System.out.println("'" + SHOW_PROJECT_LIST + "' shows list of projects");
        System.out.println("'" + CREATE_PROJECT + "' creates new project");
        System.out.println("'" + REMOVE_PROJECT + " %number%' removes project with specified number");
       // System.out.println("'" + GO_INTO_PROJECT +  " %number%' enter in project submenu");
    }
    private static void setCurrentProject(String projectId, ProjectManipulator pm){
        if(projectId.isEmpty()) {
            currentProjectId="";
            return;
        }
        if(pm.checkID(projectId)) currentProjectId=projectId;
    }
}
