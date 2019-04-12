package ru.karelin.tmclient;


import ru.karelin.tmclient.command.*;
import ru.karelin.tmclient.util.Bootstrap;
import ru.karelin.tmserver.endpoint.ProjectEndpointService;

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;

public class Application {

    public static void main(String[] args) {
        SeContainerInitializer.newInstance().initialize();
        Bootstrap bootstrap = CDI.current().select(Bootstrap.class).get();
        Class[] commandClasses = {
                ProjectCreateCommand.class,
                ProjectEditCommand.class,
                ProjectShowCommand.class,
                ProjectListShowCommand.class,
                ProjectRemoveCommand.class,

                TaskCreateCommand.class,
                TaskEditCommand.class,
                TaskShowCommand.class,
                TaskListShowCommand.class,
                TaskRemoveCommand.class,

                UserRegisterCommand.class,
                UserAuthorizationCommand.class,
                UserShowCurrentCommand.class,
                UserLogoutCommand.class,
                UserPasswordChangeCommand.class,
                UserProfileEditCommand.class,

                HelpShowCommand.class,
                InfoShowCommand.class
        };
        bootstrap.init(commandClasses);
    }
}
