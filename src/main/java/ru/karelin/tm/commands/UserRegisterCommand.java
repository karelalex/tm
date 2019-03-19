package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.service.UserService;

import java.io.Console;
import java.util.Arrays;

public class UserRegisterCommand extends AbstractCommand {
    public UserRegisterCommand(String name, String description, Bootstrap bootstrap) {
        super("register", "starts new user registration dialog", bootstrap);
    }

    @Override
    public void execute(String... params) {
        UserService userService = bootstrap.getUserService();
        System.out.println("Enter login");
        String login = sc.nextLine();
        Console console = System.console();
        while (userService.isUserExistByLogin(login)) {
            System.out.println("Choose another login");
            login = sc.nextLine();
        }
        char[] pass, passRepeat;
        while (true){
            System.out.println("Enter password");
            pass = console.readPassword();
            System.out.println("Repeat password");
            passRepeat = console.readPassword();
            if(!Arrays.equals(pass, passRepeat)){
                System.out.println("Passwords don't match, try again");
                continue;
            }
            break;
        }
        System.out.println("Enter your name");
        String name = sc.nextLine();
        userService.registerNewUser(login, pass, name);
        System.out.println("Thank you for registration");
    }

}
