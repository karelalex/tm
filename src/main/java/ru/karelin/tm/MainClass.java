package ru.karelin.tm;

import static ru.karelin.tm.Statics.*;

import java.util.Scanner;


public class MainClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String command;
        String[] commandParts;
        while (true) {
            System.out.print(">");
            command = sc.nextLine();
            commandParts = command.split(" ");

            if (command.equals(QUIT)) break;
            if (command.equals(HELP)){
               showMainHelp();
            }
            if (command.equals(CREATE_PROJECT)) {
                System.out.println("Enter project description.");
                int projectNumber = ProjectManipulator.createProject(sc.nextLine());
                enterProject(projectNumber, sc);
            }

            if (command.equals(SHOW_PROJECT_LIST)) {
                ProjectManipulator.showProjectsList();
            }

            if (commandParts[0].equals(REMOVE_PROJECT)) {
                int projectNumber;
                if (commandParts.length > 1 && commandParts[1].matches("^-?\\d+$") && ProjectManipulator.checkProjectListBounds(projectNumber = Integer.parseInt(commandParts[1]))) {
                    System.out.println("Remove project #" + projectNumber + "? (y/n)");
                    if (sc.nextLine().equalsIgnoreCase("y")) {
                        ProjectManipulator.removeProject(projectNumber);
                    }
                }
            }

            if (commandParts[0].equals(GO_INTO_PROJECT)) {
                int projectNumber;
                if (commandParts.length > 1 && commandParts[1].matches("^-?\\d+$") && ProjectManipulator.checkProjectListBounds(projectNumber = Integer.parseInt(commandParts[1]))) {
                    enterProject(projectNumber, sc);
                }
            }
        }
    }

    private static void enterProject(int projectNumber, Scanner sc) {
        String command;
        String[] commandParts;
        while (true) {
            System.out.print("project " + projectNumber + ">");
            command = sc.nextLine();
            commandParts = command.split(" ");

            if (command.equals(LEAVE_PROJECT)) break;
            if (command.equals(HELP)){
                showProjectHelp();
            }
            if (command.equals(EDIT_PROJECT_DESCRIPTION)) {
                System.out.println("Enter project description");
                ProjectManipulator.editProjectDescription(projectNumber, sc.nextLine());
            }

            if (command.equals(ADD_TASK_TO_PROJECT)) {
                System.out.println("Enter new task description");
                ProjectManipulator.addNewTask(projectNumber, sc.nextLine());
            }

            if (command.equals(SHOW_PROJECT_TASK_LIST)) {
                ProjectManipulator.showTaskList(projectNumber);
            }

            if (commandParts[0].equals(SHOW_PROJECT_TASK)) {
                int taskNumber;
                if (commandParts.length > 1 && commandParts[1].matches("^-?\\d+$") && ProjectManipulator.checkTaskListBounds(projectNumber, (taskNumber = Integer.parseInt(commandParts[1])))) {
                    ProjectManipulator.showTask(projectNumber, taskNumber);
                }
            }

            if (commandParts[0].equals(EDIT_PROJECT_TASK)) {
                int taskNumber;
                if (commandParts.length > 1 && commandParts[1].matches("^-?\\d+$") && ProjectManipulator.checkTaskListBounds(projectNumber, (taskNumber = Integer.parseInt(commandParts[1])))) {
                    System.out.println("Enter new task description");
                    ProjectManipulator.editTask(projectNumber, taskNumber, sc.nextLine());
                }
            }

            if (commandParts[0].equals(REMOVE_PROJECT_TASK)) {
                int taskNumber;
                if (commandParts.length > 1 && commandParts[1].matches("^-?\\d+$") && ProjectManipulator.checkTaskListBounds(projectNumber, (taskNumber = Integer.parseInt(commandParts[1])))) {
                    System.out.println("Do you really want to remove task No " + taskNumber);
                    if (sc.nextLine().equalsIgnoreCase("y"))
                        ProjectManipulator.removeTask(projectNumber, taskNumber);
                }
            }

            if (command.equals(REMOVE_PROJECT)) {
                System.out.println("Remove project #" + projectNumber + "? (y/n)");
                if (sc.nextLine().equalsIgnoreCase("y")) {
                    ProjectManipulator.removeProject(projectNumber);
                    break;
                }
            }


        }
    }

    private static void showProjectHelp() {
        System.out.println("Commmands: ");
        System.out.println("'" + LEAVE_PROJECT + "' exit to main menu");
        System.out.println("'" + EDIT_PROJECT_DESCRIPTION + "' allows to change description of current project");
        System.out.println("'" + SHOW_PROJECT_TASK_LIST + "' shows task list of current project");
        System.out.println("'" + SHOW_PROJECT_TASK +  " %number%' shows task with specified number in current project ");
        System.out.println("'" + ADD_TASK_TO_PROJECT + "' adds task to current project");
        System.out.println("'" + EDIT_PROJECT_TASK +  " %number%' allows to edit task with specified number in current project ");
        System.out.println("'" + REMOVE_PROJECT_TASK +  " %number%' removes task with specified number from current project ");
        System.out.println("'" + REMOVE_PROJECT + "' removes current project");
    }

    private static void showMainHelp(){
        System.out.println("Commmands: ");
        System.out.println("'" + SHOW_PROJECT_LIST + "' shows list of projects");
        System.out.println("'" + CREATE_PROJECT + "' creates new project");
        System.out.println("'" + REMOVE_PROJECT + " %number%' removes project with specified number");
        System.out.println("'" + GO_INTO_PROJECT +  " %number%' enter in project submenu");
    }
}
