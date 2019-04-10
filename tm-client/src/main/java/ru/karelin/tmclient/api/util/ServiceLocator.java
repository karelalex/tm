package ru.karelin.tmclient.api.util;


import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.command.AbstractCommand;
import ru.karelin.tmclient.util.DateConverter;
import ru.karelin.tmserver.endpoint.*;

import java.text.DateFormat;
import java.util.Map;

public interface ServiceLocator {
    SessionDto getCurrentSession();

    void setCurrentSession(SessionDto session);

    Map<String, AbstractCommand> getCommands();

    DateFormat getDateFormat();

    TerminalService getTerminalService();

    UserEndpoint getUserEndpoint();

    @NotNull
    DateConverter getDateConverter();

    ProjectEndpoint getProjectEndpoint();

    TaskEndpoint getTaskEndpoint();

    DomainEndpoint getDomainEndpoint();

    @NotNull
    SessionEndpoint getSessionEndpoint();

    void init(Class[] commandClasses);
}
