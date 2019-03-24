package ru.karelin.tm;

import ru.karelin.tm.command.*;
import ru.karelin.tm.util.Bootstrap;

import java.io.IOException;

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
                InfoShowCommand.class};
        try {
            new Bootstrap().init(commandClasses);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
