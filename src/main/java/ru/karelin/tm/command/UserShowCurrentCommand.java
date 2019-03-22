package ru.karelin.tm.command;

import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.entity.User;

public final class UserShowCurrentCommand extends AbstractCommand {

    private static final boolean SECURED = true;

    public UserShowCurrentCommand(ServiceLocator locator) {
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
    public void execute(final String... params) {
        final User currentUser = locator.getCurrentUser();
        System.out.println("Login: "+ currentUser.getLogin());
        System.out.println("Name: " + currentUser.getUserName());
        System.out.println("Role: " + currentUser.getRole());
    }
}
