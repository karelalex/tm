package ru.karelin.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.api.service.UserService;

import java.io.Console;
import java.util.Arrays;

public final class UserRegisterCommand extends AbstractCommand {

    private static final boolean SECURED = false;

    public UserRegisterCommand(@NotNull ServiceLocator locator) {
        super(locator, SECURED);
    }
    public UserRegisterCommand(){super(SECURED);}

    @Override
    public String getName() {
        return "reg";
    }

    @Override
    public String getDescription() {
        return "starts new user registration dialog";
    }

    @Override
    public void execute(final String... params) {
        @NotNull final UserService userService = locator.getUserService();
        System.out.println("Enter login");
        @NotNull String login = ts.readLn();
        while (userService.isUserExistByLogin(login)) {
            System.out.println("Choose another login");
            login = ts.readLn();
        }
        char[] pass, passRepeat;
        while (true){
            System.out.println("Enter password");
            pass = ts.readPass();
            System.out.println("Repeat password");
            passRepeat = ts.readPass();
            if(!Arrays.equals(pass, passRepeat)){
                System.out.println("Passwords don't match, try again");
                continue;
            }
            break;
        }
        System.out.println("Enter your name");
        @NotNull final String name = ts.readLn();
        userService.registerNewUser(login, pass, name);
        System.out.println("Thank you for registration");
    }

}
