package ru.karelin.tm.api.util;

import ru.karelin.tm.command.AbstractCommand;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.TaskService;
import ru.karelin.tm.api.service.UserService;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Map;
import java.util.Scanner;

public interface ServiceLocator {
    User getCurrentUser();

    void setCurrentUser(User currentUser);

    Map<String, AbstractCommand> getCommands();

    DateFormat getDateFormat();

    TerminalService getTerminalService();

    UserService getUserService();

    ProjectService getProjectService();

    TaskService getTaskService();

    void init(Class[] commandClasses) throws IOException;
}
