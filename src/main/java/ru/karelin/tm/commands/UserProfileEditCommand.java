package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.service.UserService;

import javax.xml.ws.Service;

public class UserProfileEditCommand extends AbstractCommand{
    public UserProfileEditCommand(ServiceLocator locator) {
        super(locator, true);
    }

    @Override
    public String getName() {
        return "eu";
    }

    @Override
    public String getDescription() {
        return "starts edit user dialog";
    }

    @Override
    public void execute(String... params) {
        System.out.println("Enter user name or just press enter if you do not want to change it");
        String userName = sc.nextLine();
        UserService userService = locator.getUserService();
        userService.editUser(locator.getCurrentUser(), userName);
    }
}
