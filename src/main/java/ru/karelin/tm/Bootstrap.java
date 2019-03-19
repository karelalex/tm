package ru.karelin.tm;

import ru.karelin.tm.commands.*;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.repository.*;
import ru.karelin.tm.service.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Bootstrap implements ServiceLocator {

    public static final String QUIT = "exit";


    private ProjectService projectService;
    private TaskService taskService;
    private UserService userService;
    private Scanner sc;
    private Map<String, AbstractCommand> commands = new LinkedHashMap<>();
    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
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
        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        TaskRepository taskRepository = new TaskRepositoryImpl();
        projectService = new ProjectServiceImpl(projectRepository, taskRepository);
        taskService = new TaskServiceImpl(taskRepository);
        MD5Generator md5Generator = new MD5Generator();
        UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(md5Generator, userRepository);
        sc = new Scanner(System.in);


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


        //end of command registration block

        // create two users block
        userService.registerNewUser("sk", "pp".toCharArray(), "alex", RoleType.ORDINARY_USER);
        userService.registerNewUser("bb", "ee".toCharArray(), "boris", RoleType.ADMIN);

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


    }

    private void registerCommand(AbstractCommand command) {
        String commandName = command.getName();
        if (commands.containsKey(commandName))
            throw new ObjectAlreadyExistsException("Command with name " + commandName + " is already registered");
        commands.put(commandName, command);
    }
}
