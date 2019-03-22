package ru.karelin.tm.command;

import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.api.service.UserService;

import java.io.Console;
import java.util.Arrays;

public final class UserPasswordChangeCommand extends AbstractCommand {

    private static final boolean SECURED = true;

    public UserPasswordChangeCommand(ServiceLocator locator) {
        super(locator, SECURED);
    }
    public UserPasswordChangeCommand(){super(SECURED);}

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
        final char[] oldPass;
        char[] newPass, newPassRepeat;
        oldPass = ts.readPass();
        while (true){
            System.out.println("Enter new password");
            newPass = ts.readPass();
            System.out.println("Repeat new password");
            newPassRepeat = ts.readPass();
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
