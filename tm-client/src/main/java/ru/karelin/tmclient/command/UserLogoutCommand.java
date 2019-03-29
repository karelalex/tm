package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;


public final class UserLogoutCommand extends AbstractCommand {

    private static final boolean SECURED = false;

    public UserLogoutCommand(@NotNull ServiceLocator locator) {
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
