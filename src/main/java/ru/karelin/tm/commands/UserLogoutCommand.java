package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;

public class UserLogoutCommand extends AbstractCommand {
    public UserLogoutCommand(ServiceLocator locator) {
        super(locator, false);
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
        locator.setCurrentUser(null);
        System.out.println("You have successfully logged out");
    }
}
