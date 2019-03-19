package ru.karelin.tm;

import ru.karelin.tm.commands.AbstractCommand;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.service.ProjectService;
import ru.karelin.tm.service.TaskService;
import ru.karelin.tm.service.UserService;

import java.text.DateFormat;
import java.util.Map;
import java.util.Scanner;

public interface ServiceLocator {
    User getCurrentUser();

    void setCurrentUser(User currentUser);

    Map<String, AbstractCommand> getCommands();

    DateFormat getDateFormat();

    Scanner getScanner();

    UserService getUserService();

    ProjectService getProjectService();

    TaskService getTaskService();

    void init();
}
