package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.User;
import ru.karelin.tmserver.endpoint.UserEndpoint;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;


public final class UserShowCurrentCommand extends AbstractCommand {

    private static final boolean SECURED = true;

    public UserShowCurrentCommand(@NotNull ServiceLocator locator) {
        super(locator, SECURED);
    }
    public UserShowCurrentCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "su";
    }

    @Override
    public String getDescription() {
        return "shows current user description";
    }

    @Override
    public void execute(final String... params) throws WrongSessionException_Exception {
        @NotNull final UserEndpoint userEndpoint = locator.getUserEndpoint();
        @NotNull final User currentUser = userEndpoint.getCurrentUser(locator.getCurrentSession());
        System.out.println("Login: "+ currentUser.getLogin());
        System.out.println("Name: " + currentUser.getUserName());
        System.out.println("Role: " + currentUser.getRole());
    }
}
