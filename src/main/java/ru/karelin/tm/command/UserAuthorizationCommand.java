package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.api.service.UserService;
import ru.karelin.tm.entity.User;

import java.io.Console;


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
    public void execute(final String... params) {
        @NotNull final UserService userService = locator.getUserService();
        System.out.println("Enter login");
        @NotNull final String login = ts.readLn();
        System.out.println("Enter password");
        final char[] pass = ts.readPass();
        @Nullable final User user = userService.getUserByLoginAndPassword(login, pass);
        if(user==null){
            System.out.println("Wrong login or pass");
        }
        else {
            locator.setCurrentUser(user);
            System.out.println("Welcome " + user.getUserName());
        }

    }

}
