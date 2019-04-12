package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.SessionEndpoint;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserLogoutCommand extends AbstractCommand {

    @Inject
    private ServiceLocator locator;

    @Inject
    private SessionEndpoint sessionEndpoint;

    private static final boolean SECURED = false;

    public UserLogoutCommand() {
        super(SECURED);
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
    public void execute(final String... params) throws WrongSessionException_Exception {
        sessionEndpoint.logout(locator.getCurrentSession());
        locator.setCurrentSession(null);
        System.out.println("You have successfully logged out");
    }
}
