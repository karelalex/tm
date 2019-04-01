package ru.karelin.tmserver.api.util;

import ru.karelin.tmserver.api.service.DomainService;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.entity.User;
import java.text.DateFormat;


public interface ServiceLocator {

    DateFormat getDateFormat();

    UserService getUserService();

    ProjectService getProjectService();

    TaskService getTaskService();

    DomainService getDomainService();

    void init();
}
