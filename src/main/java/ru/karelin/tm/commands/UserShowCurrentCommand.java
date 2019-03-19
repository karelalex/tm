package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.ServiceLocator;
import ru.karelin.tm.entity.User;

public class UserShowCurrentCommand extends AbstractCommand {
    public UserShowCurrentCommand(ServiceLocator locator) {
        super(locator, true);
    }

    @Override
    public String getName() {
        return "su";
    }

    @Override
    public String getDescription() {
        return "shows current user description";
    }

    @Override
    public void execute(String... params) {
        User currentUser = locator.getCurrentUser();
        System.out.println("Login: "+ currentUser.getLogin());
        System.out.println("Name: " + currentUser.getUserName());
        System.out.println("Role: " + currentUser.getRole());
    }
}
