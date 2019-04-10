package ru.karelin.tmclient;


import ru.karelin.tmclient.command.*;
import ru.karelin.tmclient.util.Bootstrap;

public class Application {

    public static void main(String[] args) {
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
        new Bootstrap().init(commandClasses);

    }
}
