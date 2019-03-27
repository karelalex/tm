package ru.karelin.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.api.service.DomainService;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public final class Bootstrap implements ServiceLocator {

    private static final String QUIT = "exit";


    @NotNull private ProjectService projectService;
    @NotNull private TaskService taskService;
    @NotNull private UserService userService;
    @NotNull private DomainService domainService;



    @NotNull private final TerminalService terminalService= new TerminalServiceImpl();
    @NotNull private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();
    @NotNull private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @Nullable private User currentUser;

    @Override
    @Nullable public User getCurrentUser() {
        return currentUser;
    }

    @NotNull
    @Override
    public TerminalService getTerminalService() {
        return terminalService;
    }
    @Override
    public void setCurrentUser(@Nullable User currentUser) {
        this.currentUser = currentUser;
    }

    @NotNull
    @Override
    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }


    @NotNull
    @Override
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    @NotNull
    public DomainService getDomainService() {
        return domainService;
    }

    @Override
    @NotNull public UserService getUserService() {
        return userService;
    }

    @Override
    @NotNull public ProjectService getProjectService() {
        return projectService;
    }

    @Override
    @NotNull public TaskService getTaskService() {
        return taskService;
    }

    @Override
    public void init(Class[] commandClasses) {
        @NotNull final ProjectRepository projectRepository = new ProjectRepositoryImpl();
        @NotNull final TaskRepository taskRepository = new TaskRepositoryImpl();
        projectService = new ProjectServiceImpl(projectRepository, taskRepository);
        taskService = new TaskServiceImpl(taskRepository);
        @NotNull final MD5Generator md5Generator = new MD5Generator();
        @NotNull final UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(md5Generator, userRepository);
        domainService = new DomainServiceImpl(userRepository, taskRepository, projectRepository);


        //command registration block
        for (Class commandClass : commandClasses) {
            try {
                registerCommand(commandClass);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

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
                try{
                    abstractCommand.execute(params);
                }
            catch (Exception e){
                    e.printStackTrace();
            }
            else System.out.println("Login first.");
        }

        // end of main loop


    }

    private void registerCommand(@NotNull final Class commandClass) throws IllegalAccessException, InstantiationException {
        if (AbstractCommand.class.isAssignableFrom(commandClass)) {
            AbstractCommand command = (AbstractCommand) commandClass.newInstance();
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
