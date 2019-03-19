package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.service.UserService;

import java.io.Console;
import java.util.Arrays;

public class UserAuthorizationCommand extends AbstractCommand {
    public UserAuthorizationCommand(Bootstrap bootstrap) {
        super(bootstrap, false);
    }

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public String getDescription() {
        return "starts user authorization dialog";
    }

    @Override
    public void execute(String... params) {
        UserService userService = bootstrap.getUserService();
        System.out.println("Enter login");
        String login = sc.nextLine();
        System.out.println("Enter password");
        Console console = System.console();
        char[] pass = console.readPassword();;
        User user = userService.getUserByLoginAndPassword(login, pass);
        if(user==null){
            System.out.println("Wrong login or pass");
        }
        else {
            bootstrap.setCurrentUser(user);
            System.out.println("Welcome " + user.getUserName());
        }

    }

}
