package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.service.UserService;

import java.io.Console;
import java.util.Arrays;

public final class UserPasswordChangeCommand extends AbstractCommand {

    public UserPasswordChangeCommand(ServiceLocator locator) {
        super(locator, true);
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
        final UserService userService = locator.getUserService();
        final String currentUserId = locator.getCurrentUser().getId();
        System.out.println("Enter your old pass");
        final Console console = System.console();
        final char[] oldPass;
        char[] newPass, newPassRepeat;
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
        final boolean success = userService.changePassword(currentUserId, oldPass, newPass);
        if(success) System.out.println("Password was changed");
        else System.out.println("Password was NOT changed. Make sure you typed correct old password");

    }

}
