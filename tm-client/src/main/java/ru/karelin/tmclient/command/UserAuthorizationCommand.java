package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserAuthorizationCommand extends AbstractCommand {

    @Inject
    private SessionEndpoint sessionEndpoint;

    @Inject
    private UserEndpoint userEndpoint;

    @Inject
    private ServiceLocator locator;

    private static final boolean SECURED = false;

    public UserAuthorizationCommand() {
        super(SECURED);
    }

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
        System.out.println("Enter login");
        @NotNull final String login = ts.readLn();
        System.out.println("Enter password");
        final char[] pass = ts.readPass();
        @Nullable final SessionDto session = sessionEndpoint.login(login, new String(pass));
        if (session == null) {
            System.out.println("Wrong login or pass");
        } else {
            locator.setCurrentSession(session);
            System.out.println("Welcome " + userEndpoint.getCurrentUser(session).getUserName());
        }

    }

}
