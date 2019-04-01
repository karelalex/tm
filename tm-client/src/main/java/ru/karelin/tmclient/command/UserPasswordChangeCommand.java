package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmserver.endpoint.Session;
import ru.karelin.tmserver.endpoint.UserEndpoint;


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
        @NotNull final UserEndpoint userEndpoint = locator.getUserEndpoint();
        @NotNull final Session session = locator.getCurrentSession();
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
        final boolean success = userEndpoint.changePassword(session, new String(oldPass),new String( newPass));
        if(success) System.out.println("Password was changed");
        else System.out.println("Password was NOT changed. Make sure you typed correct old password");

    }

}
