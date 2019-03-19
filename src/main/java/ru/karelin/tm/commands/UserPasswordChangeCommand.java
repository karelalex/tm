package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.service.UserService;

import java.io.Console;
import java.util.Arrays;

public class UserPasswordChangeCommand extends AbstractCommand {

    public UserPasswordChangeCommand(Bootstrap bootstrap) {
        super(bootstrap, true);
    }

    @Override
    public String getName() {
        return "pc";
    }

    @Override
    public String getDescription() {
        return "starts password change dialog";
    }

    @Override
    public void execute(String... params) {
        UserService userService = locator.getUserService();
        User currentUser = locator.getCurrentUser();
        System.out.println("Enter your old pass");
        Console console = System.console();
        char[] oldPass, newPass, newPassRepeat;
        oldPass = console.readPassword();
        while (true){
            System.out.println("Enter new password");
            newPass = console.readPassword();
            System.out.println("Repeat new password");
            newPassRepeat = console.readPassword();
            if(!Arrays.equals(newPass, newPassRepeat)){
                System.out.println("Passwords don't match, try again");
                continue;
            }
            break;
        }
        Boolean success = userService.changePassword(currentUser, oldPass, newPass);
        if(success) System.out.println("Password was changed");
        else System.out.println("Password was NOT changed. Make sure you typed correct old password");

    }

}
