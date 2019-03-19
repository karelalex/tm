package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.service.UserService;

import java.io.Console;
import java.util.Arrays;

public class UserAuthorizationCommand extends AbstractCommand {
    public UserAuthorizationCommand(String name, String description, Bootstrap bootstrap) {
        super("login", "starts user authorization dialog", bootstrap);
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
