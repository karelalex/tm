package ru.karelin.tm.commands;

import ru.karelin.tm.api.ServiceLocator;

public final class UserLogoutCommand extends AbstractCommand {
    public UserLogoutCommand(ServiceLocator locator) {
        super(locator, false);
    }

    @Override
    public String getName() {
        return "out";
    }

    @Override
    public String getDescription() {
        return "finishes user session";
    }

    @Override
    public void execute(final String... params) {
        locator.setCurrentUser(null);
        System.out.println("You have successfully logged out");
    }
}
