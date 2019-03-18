package ru.karelin.tm;

import ru.karelin.tm.commands.*;
import ru.karelin.tm.repository.ProjectRepository;
import ru.karelin.tm.repository.TaskRepository;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static ru.karelin.tm.Statics.*;


public class Bootstrap {

    private ProjectService projectService;
    private TaskService taskService;
    private Scanner sc;

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Scanner getScanner() {
        return sc;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void init() {
        ProjectRepository projectRepository = new ProjectRepository();
        TaskRepository taskRepository = new TaskRepository();
        projectService = new ProjectService(projectRepository, taskRepository);
        taskService = new TaskService(taskRepository);
        sc = new Scanner(System.in);
        Map<String, AbstractCommand> commands = new HashMap<>();

        //commands registration block

        ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand(this);
        commands.put(projectCreateCommand.getName(), projectCreateCommand);
        ProjectEditCommand projectEditCommand = new ProjectEditCommand(this);
        commands.put(projectEditCommand.getName(), projectEditCommand);
        ProjectListShowCommand projectListShowCommand = new ProjectListShowCommand(this);
        commands.put(projectListShowCommand.getName(), projectListShowCommand);
        ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand(this);
        commands.put(projectRemoveCommand.getName(), projectRemoveCommand);
        ProjectShowCommand projectShowCommand = new ProjectShowCommand(this);
        commands.put(projectShowCommand.getName(), projectShowCommand);




        //end of command registration block

        String command;
        String[] commandParts, params;
        out:

        while (true) {
            System.out.print(">");
            command = sc.nextLine();
            commandParts = command.split(" ");

            command = commandParts[0];
            params = Arrays.copyOfRange(commandParts, 1, commandParts.length);
            AbstractCommand abstractCommand = commands.get(command);
            if(abstractCommand==null){
                System.out.println("Wrong command name");
                continue ;
            }
            abstractCommand.execute(params);

            /*
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
                    System.out.println("Wrong commandId");
*/
            }
        }


    public void showMainHelp() {
        System.out.println("Commands: ");
        System.out.println("'" + SHOW_PROJECT_LIST + "' shows list of projects");
        System.out.println("'" + CREATE_PROJECT + "' starts create new project dialog");
        System.out.println("'" + REMOVE_PROJECT + " %number%' ");
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
