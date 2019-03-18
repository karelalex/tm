package ru.karelin.tm;

import ru.karelin.tm.commands.*;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.repository.ProjectRepository;
import ru.karelin.tm.repository.TaskRepository;
import ru.karelin.tm.repository.UserRepository;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;
import ru.karelin.tm.service.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Bootstrap {

    public static final String QUIT = "exit";


    private ProjectService projectService;
    private TaskService taskService;
    private UserService userService;
    private Scanner sc;
    private Map<String, AbstractCommand> commands = new LinkedHashMap<>();
    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }


    public DateFormat getDateFormat() {
        return dateFormat;
    }


    public Scanner getScanner() {
        return sc;
    }

    public UserService getUserService() {
        return userService;
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
        MD5Generator md5Generator = new MD5Generator();
        UserRepository userRepository = new UserRepository();
        userService = new UserService(md5Generator, userRepository);
        sc = new Scanner(System.in);


        //commands registration block
        HelpShowCommand helpShowCommand = new HelpShowCommand(this);
        commands.put(helpShowCommand.getName(), helpShowCommand);

        ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand(this);
        commands.put(projectCreateCommand.getName(), projectCreateCommand);
        ProjectEditCommand projectEditCommand = new ProjectEditCommand(this);
        commands.put(projectEditCommand.getName(), projectEditCommand);
        ProjectShowCommand projectShowCommand = new ProjectShowCommand(this);
        commands.put(projectShowCommand.getName(), projectShowCommand);
        ProjectListShowCommand projectListShowCommand = new ProjectListShowCommand(this);
        commands.put(projectListShowCommand.getName(), projectListShowCommand);
        ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand(this);
        commands.put(projectRemoveCommand.getName(), projectRemoveCommand);

        TaskCreateCommand taskCreateCommand = new TaskCreateCommand(this);
        commands.put(taskCreateCommand.getName(), taskCreateCommand);
        TaskEditCommand taskEditCommand = new TaskEditCommand(this);
        commands.put(taskEditCommand.getName(), taskEditCommand);
        TaskShowCommand taskShowCommand = new TaskShowCommand(this);
        commands.put(taskShowCommand.getName(), taskShowCommand);
        TaskListShowCommand taskListShowCommand = new TaskListShowCommand(this);
        commands.put(taskListShowCommand.getName(), taskListShowCommand);
        TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand(this);
        commands.put(taskRemoveCommand.getName(), taskRemoveCommand);

        //end of command registration block

        String command;
        String[] commandParts, params;


        while (true) {
            System.out.print(">");
            command = sc.nextLine();
            if(command.equals(QUIT)) break;
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


   }
