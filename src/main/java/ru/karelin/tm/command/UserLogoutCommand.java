package ru.karelin.tm.command;

import ru.karelin.tm.api.util.ServiceLocator;


public final class UserLogoutCommand extends AbstractCommand {

    private static final boolean SECURED = false;

    public UserLogoutCommand(ServiceLocator locator) {
        super(locator, SECURED);
    }
    public UserLogoutCommand(){super(SECURED);}

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
