package ru.karelin.tmclient.api.util;

import ru.karelin.tm.api.service.DomainService;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.api.service.TaskService;
import ru.karelin.tm.api.service.UserService;
import ru.karelin.tm.command.AbstractCommand;
import ru.karelin.tm.entity.User;

import java.text.DateFormat;
import java.util.Map;

public interface ServiceLocator {
    User getCurrentUser();

    void setCurrentUser(User currentUser);

    Map<String, AbstractCommand> getCommands();

    DateFormat getDateFormat();

    TerminalService getTerminalService();

    UserService getUserService();

    ProjectService getProjectService();

    TaskService getTaskService();

    DomainService getDomainService();

    void init(Class[] commandClasses);
}
