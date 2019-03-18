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




public class Bootstrap {

    public static final String QUIT = "exit";
    public static final String HELP = "help";

    private ProjectService projectService;
    private TaskService taskService;
    private Scanner sc;

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    private Map<String, AbstractCommand> commands = new HashMap<>();

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
        TaskCreateCommand taskCreateCommand = new TaskCreateCommand(this);
        commands.put(taskCreateCommand.getName(), taskCreateCommand);
        TaskEditCommand taskEditCommand = new TaskEditCommand(this);
        commands.put(taskEditCommand.getName(), taskEditCommand);
        TaskListShowCommand taskListShowCommand = new TaskListShowCommand(this);
        commands.put(taskListShowCommand.getName(), taskListShowCommand);
        TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand(this);
        commands.put(taskRemoveCommand.getName(), taskRemoveCommand);
        TaskShowCommand taskShowCommand = new TaskShowCommand(this);
        commands.put(taskShowCommand.getName(), taskShowCommand);

        //end of command registration block

        String command;
        String[] commandParts, params;
        out:

        while (true) {
            System.out.print(">");
            command = sc.nextLine();
            if(command.equals(QUIT)) break;
            if (command.equals(HELP)) {
                showMainHelp();
                continue;
            }
            commandParts = command.split(" ");

            command = commandParts[0];
            params = Arrays.copyOfRange(commandParts, 1, commandParts.length);
            AbstractCommand abstractCommand = commands.get(command);
            if (abstractCommand == null) {
                System.out.println("Wrong command name");
                continue;
            }
            abstractCommand.execute(params);


        }
    }


    public void showMainHelp() {
        System.out.println("Commands: ");
        for (AbstractCommand c : commands.values()
        ) {
            System.out.println("'" + c.getName() + "' " + c.getDecsription());
        }
        System.out.println("'" + QUIT + "' closes program");

    }


}
