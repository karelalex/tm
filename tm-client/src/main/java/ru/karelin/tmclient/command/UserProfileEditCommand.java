package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.UserEndpoint;

public final class UserProfileEditCommand extends AbstractCommand{

    private static final boolean SECURED = true;

    public UserProfileEditCommand(ServiceLocator locator) {
        super(locator, SECURED);
    }
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
    public void execute(String... params) {
        System.out.println("Enter user name or just press enter if you do not want to change it");
        @NotNull final String userName = ts.readLn();
        @NotNull final UserEndpoint userEndpoint = locator.getUserEndpoint();
        userEndpoint.editUser(locator.getCurrentUser().getId(), userName);
    }
}
