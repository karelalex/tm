package ru.karelin.tm.util;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.ServiceLocator;
import ru.karelin.tm.api.repository.ProjectRepository;
import ru.karelin.tm.api.repository.TaskRepository;
import ru.karelin.tm.api.repository.UserRepository;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.TaskService;
import ru.karelin.tm.api.service.UserService;
import ru.karelin.tm.commands.*;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.enums.RoleType;
import ru.karelin.tm.exception.ObjectAlreadyExistsException;
import ru.karelin.tm.repository.*;
import ru.karelin.tm.service.*;
import ru.karelin.tm.util.MD5Generator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public final class Bootstrap implements ServiceLocator {

    public static final String QUIT = "exit";


    private ProjectService projectService;
    private TaskService taskService;
    private UserService userService;
    private final Scanner sc= new Scanner(System.in);
    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private User currentUser;

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }


    @Override
    public DateFormat getDateFormat() {
        return dateFormat;
    }


    @Override
    public Scanner getScanner() {
        return sc;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public ProjectService getProjectService() {
        return projectService;
    }

    @Override
    public TaskService getTaskService() {
        return taskService;
    }

    @Override
    public void init() {
        final ProjectRepository projectRepository = new ProjectRepositoryImpl();
        final TaskRepository taskRepository = new TaskRepositoryImpl();
        projectService = new ProjectServiceImpl(projectRepository, taskRepository);
        taskService = new TaskServiceImpl(taskRepository);
        final MD5Generator md5Generator = new MD5Generator();
        final UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(md5Generator, userRepository);



        //commands registration block
        registerCommand(new HelpShowCommand(this));

        registerCommand(new ProjectCreateCommand(this));
        registerCommand(new ProjectEditCommand(this));
        registerCommand(new ProjectShowCommand(this));
        registerCommand(new ProjectListShowCommand(this));
        registerCommand(new ProjectRemoveCommand(this));

        registerCommand(new TaskCreateCommand(this));
        registerCommand(new TaskEditCommand(this));
        registerCommand(new TaskShowCommand(this));
        registerCommand(new TaskListShowCommand(this));
        registerCommand(new TaskRemoveCommand(this));

        registerCommand(new UserRegisterCommand(this));
        registerCommand(new UserAuthorizationCommand(this));
        registerCommand(new UserShowCurrentCommand(this));
        registerCommand(new UserPasswordChangeCommand(this));
        registerCommand(new UserProfileEditCommand(this));
        registerCommand(new UserLogoutCommand(this));

        registerCommand(new InfoShowCommand(this));


        //end of command registration block

        // create two users block
        userService.registerNewUser("sk", "pp".toCharArray(), "alex", RoleType.ORDINARY_USER);
        userService.registerNewUser("bb", "ee".toCharArray(), "boris", RoleType.ADMIN);

        // end of create two users block

        // main loop

        String command;
        String[] commandParts, params;
        while (true) {
            System.out.print(">");
            command = sc.nextLine();
            if (command.equals(QUIT)) break;
            commandParts = command.split(" ");

            command = commandParts[0];
            params = Arrays.copyOfRange(commandParts, 1, commandParts.length);
            AbstractCommand abstractCommand = commands.get(command);
            if (abstractCommand == null) {
                System.out.println("Wrong command name");
                continue;
            }
            if (!abstractCommand.isSecured() || currentUser != null)
                abstractCommand.execute(params);
            else System.out.println("Login first.");
        }

        // end of main loop


    }

    private void registerCommand(@NotNull final AbstractCommand command) {
        String commandName = command.getName();
        if (commands.containsKey(commandName))
            throw new ObjectAlreadyExistsException("Command with name " + commandName + " is already registered");
        commands.put(commandName, command);
    }
}
