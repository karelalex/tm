package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.service.UserService;

import javax.sound.midi.Soundbank;
import java.io.Console;

public class UserRegisterCommand extends AbstractCommand {
    public UserRegisterCommand(String name, String description, Bootstrap bootstrap) {
        super("cu", "starts new user registration dialog", bootstrap);
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
    }

}
