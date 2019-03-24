package ru.karelin.tm.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.api.repository.ProjectRepository;
import ru.karelin.tm.api.repository.TaskRepository;
import ru.karelin.tm.api.repository.UserRepository;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.TaskService;
import ru.karelin.tm.api.service.UserService;
import ru.karelin.tm.api.util.TerminalService;
import ru.karelin.tm.command.*;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.enumeration.RoleType;
import ru.karelin.tm.exception.CommandRegisteredException;
import ru.karelin.tm.repository.*;
import ru.karelin.tm.service.*;

import java.io.IOException;
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



    private final TerminalService terminalService= new TerminalServiceImpl();
    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private User currentUser;

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public TerminalService getTerminalService() {
        return terminalService;
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
    public void init(Class[] commandClasses) throws IOException {
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        final SqlSession sqlSession = sqlSessionFactory.openSession(true);
        final UserRepository userRepository = sqlSession.getMapper(UserBatisRepo.class);
        final ProjectRepository projectRepository = sqlSession.getMapper(ProjectBatisRepo.class);
        //final ProjectRepository projectRepository = new ProjectRepositoryImpl();
        final TaskRepository taskRepository = sqlSession.getMapper(TaskBatisRepo.class);
        //final TaskRepository taskRepository = new TaskRepositoryImpl();
        projectService = new ProjectServiceImpl(projectRepository, taskRepository);
        taskService = new TaskServiceImpl(taskRepository);
        final MD5Generator md5Generator = new MD5Generator();
        //final UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(md5Generator, userRepository);


        //command registration block
        for (int i = 0; i < commandClasses.length; i++) {
            try {
                registerCommand(commandClasses[i]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

        /*registerCommand(new HelpShowCommand(this));

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

        registerCommand(new InfoShowCommand(this));*/


        //end of command registration block

        // create two users block
        //userService.registerNewUser("sk", "pp".toCharArray(), "alex", RoleType.ORDINARY_USER);
        //userService.registerNewUser("bb", "ee".toCharArray(), "boris", RoleType.ADMIN);

        // end of create two users block

        // main loop

        String command;
        String[] commandParts, params;
        while (true) {
            System.out.print(">");
            command = terminalService.readLn();
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

    private void registerCommand(@NotNull final Class<AbstractCommand> commandClass) throws IllegalAccessException, InstantiationException {
        if (AbstractCommand.class.isAssignableFrom(commandClass)) {
            AbstractCommand command = commandClass.newInstance();
            final String commandName = command.getName();
            if (commands.containsKey(commandName))
                throw new CommandRegisteredException("Command with name " + commandName + " is already registered");
            command.setLocator(this);
            commands.put(commandName, command);
        } else {
            System.out.println("комманда не клёвая");
        }
    }
}
