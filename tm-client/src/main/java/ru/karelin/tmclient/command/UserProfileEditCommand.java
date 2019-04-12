package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.UserEndpoint;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserProfileEditCommand extends AbstractCommand{

    @Inject
    private UserEndpoint userEndpoint;

    @Inject
    private ServiceLocator locator;

    private static final boolean SECURED = true;

    public UserProfileEditCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "eu";
    }

    @Override
    public String getDescription() {
        return "starts edit user dialog";
    }

    @Override
    public void execute(String... params) throws WrongSessionException_Exception {
        System.out.println("Enter user name or just press enter if you do not want to change it");
        @NotNull final String userName = ts.readLn();
        userEndpoint.editUser(locator.getCurrentSession(), userName);
    }
}
