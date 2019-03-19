package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;

public class UserLogoutCommand extends AbstractCommand {
    public UserLogoutCommand(Bootstrap bootstrap) {
        super(bootstrap, false);
    }

    @Override
    public String getName() {
        return "logout";
    }

    @Override
    public String getDescription() {
        return "finishes user session";
    }

    @Override
    public void execute(String... params) {
        bootstrap.setCurrentUser(null);
        System.out.println("You have successfully logged out");
    }
}
