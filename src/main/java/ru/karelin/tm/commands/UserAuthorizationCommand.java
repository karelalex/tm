package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.service.UserService;

import java.io.Console;

public final class UserAuthorizationCommand extends AbstractCommand {
    public UserAuthorizationCommand(ServiceLocator locator) {
        super(locator, false);
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
    public void execute(final String... params) {
        final UserService userService = locator.getUserService();
        System.out.println("Enter login");
        final String login = sc.nextLine();
        System.out.println("Enter password");
        final Console console = System.console();
        final char[] pass = console.readPassword();;
        final User user = userService.getUserByLoginAndPassword(login, pass);
        if(user==null){
            System.out.println("Wrong login or pass");
        }
        else {
            locator.setCurrentUser(user);
            System.out.println("Welcome " + user.getUserName());
        }

    }

}
