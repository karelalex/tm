package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.*;


public final class UserAuthorizationCommand extends AbstractCommand {
    private static final boolean SECURED = false;

    public UserAuthorizationCommand(@NotNull ServiceLocator locator) {
        super(locator, SECURED);
    }
    public UserAuthorizationCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "in";
    }

    @Override
    public String getDescription() {
        return "starts user authorization dialog";
    }

    @Override
    public void execute(final String... params) throws WrongSessionException_Exception {
        @NotNull final SessionEndpoint sessionEndpoint = locator.getSessionEndpoint();
        @NotNull final UserEndpoint userEndpoint = locator.getUserEndpoint();
        System.out.println("Enter login");
        @NotNull final String login = ts.readLn();
        System.out.println("Enter password");
        final char[] pass = ts.readPass();
        @Nullable final Session session = sessionEndpoint.login(login, new String(pass));
        if(session==null){
            System.out.println("Wrong login or pass");
        }
        else {
            locator.setCurrentSession(session);
            System.out.println("Welcome " + userEndpoint.getCurrentUser(session).getUserName());
        }

    }

}
