package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;

public class UserLogoutCommand extends AbstractCommand {
    public UserLogoutCommand(String name, String description, Bootstrap bootstrap) {
        super("logout", "finishes user session", bootstrap);
    }

    @Override
    public void execute(String... params) {
        bootstrap.setCurrentUser(null);
        System.out.println("You have successfully logged out");
    }
}
