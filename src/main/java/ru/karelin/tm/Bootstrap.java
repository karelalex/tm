package ru.karelin.tm;

import ru.karelin.tm.repository.ProjectRepository;
import ru.karelin.tm.repository.TaskRepository;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;

import java.util.Scanner;

import static ru.karelin.tm.Statics.*;


public class Bootstrap {


    public static void init() {
        Scanner sc = new Scanner(System.in);
        ProjectRepository projectRepository = new ProjectRepository();
        TaskRepository taskRepository = new TaskRepository();
        ProjectService projectService = new ProjectService(projectRepository, taskRepository);
        TaskService taskService = new TaskService(taskRepository);
        ProjectDialog projectDialog = new ProjectDialog(projectService, sc);
        TaskDialog taskDialog = new TaskDialog(sc, taskService, projectService);

        String command;
        String[] commandParts;
        out:
        while (true) {
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
                    projectDialog.createProject();
                    break;
                case EDIT_PROJECT:
                    projectDialog.editProject(commandParts[1]);
                    break;
                case SHOW_PROJECT_LIST:
                    projectDialog.showProjectsList();
                    break;
                case SHOW_PROJECT:
                    projectDialog.showProject(commandParts[1]);
                    break;
                case REMOVE_PROJECT:
                    projectDialog.removeProject(commandParts[1]);
                    break;
                case CREATE_TASK:
                    if (commandParts.length > 1)
                        taskDialog.createTask(commandParts[1]);
                    else taskDialog.createTask();
                    break;
                case SHOW_TASK:
                    if (commandParts.length > 1) {
                        taskDialog.showTask(commandParts[1]);
                    }
                    break;
                case EDIT_TASK:
                    if (commandParts.length > 1) {
                        taskDialog.editTask(commandParts[1]);
                    }
                    break;
                case SHOW_TASK_LIST:
                    if (commandParts.length > 1) {
                        taskDialog.showTaskList(commandParts[1]);
                    }
                    else taskDialog.showTaskList("");
                    break;
                case REMOVE_TASK:
                    if (commandParts.length > 1) {
                        taskDialog.removeTask(commandParts[1]);
                    }
                    break;
                default:
                    System.out.println("Wrong commantld");

            }
        }
    }


    private static void showMainHelp() {
        System.out.println("Commmands: ");
        System.out.println("'" + SHOW_PROJECT_LIST + "' shows list of projects");
        System.out.println("'" + CREATE_PROJECT + "' starts create new project dialog");
        System.out.println("'" + REMOVE_PROJECT + " %number%' removes project with specified number and all its tasks");
        System.out.println("'" + SHOW_PROJECT + " %number%' shows project info. If current project is set %number% may be skipped");
        System.out.println("'" + EDIT_PROJECT + " %number%' starts edit project dialog for specified project. If current project is set %number% may be skipped");
        System.out.println("'" + CREATE_TASK + " %number%' starts create new task dialog. Task will be created inside current or specified project.");
        System.out.println("'" + SHOW_TASK_LIST + " %number%' shows task list for current or specified project. If not show all tasks");
        System.out.println("'" + SHOW_TASK + " %number%' shows task with specified ID.");
        System.out.println("'" + EDIT_TASK + " %number%' starts edit task dialog for specified task.");
        System.out.println("'" + REMOVE_TASK + " %number%' removes task with specified number");
        System.out.println("'" + QUIT + "' closes program");
        System.out.println("' " + HELP + "' shows this help");
    }


}
