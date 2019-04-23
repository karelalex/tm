package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmserver.endpoint.UserEndpoint;

import java.util.Arrays;

@Component
public class UserRegisterCommand extends AbstractCommand {

    @Autowired
    private UserEndpoint userEndpoint;

    private static final boolean SECURED = false;

    public UserRegisterCommand() {
        super(SECURED);
    }

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
        System.out.println("Enter login");
        @NotNull String login = ts.readLn();
        while (userEndpoint.isUserExistsByLogin(login)) {
            System.out.println("Choose another login");
            login = ts.readLn();
        }
        char[] pass, passRepeat;
        while (true) {
            System.out.println("Enter password");
            pass = ts.readPass();
            System.out.println("Repeat password");
            passRepeat = ts.readPass();
            if (!Arrays.equals(pass, passRepeat)) {
                System.out.println("Passwords don't match, try again");
                continue;
            }
            break;
        }
        System.out.println("Enter your name");
        @NotNull final String name = ts.readLn();
        userEndpoint.registerNewUser(login, new String(pass), name);
        System.out.println("Thank you for registration");
    }

}
