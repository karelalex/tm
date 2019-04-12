package ru.karelin.tmclient.command;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.karelin.tmclient.api.util.ServiceLocator;
import ru.karelin.tmclient.api.util.TerminalService;
import ru.karelin.tmserver.endpoint.WrongSessionException_Exception;

import javax.inject.Inject;

@NoArgsConstructor
public abstract class AbstractCommand {


    @Inject protected TerminalService ts;

    private boolean isSecured;

    public boolean isSecured() {
        return isSecured;
    }

    public AbstractCommand(boolean isSecured) {
        this.isSecured=isSecured;
    }


    public abstract String getName();
    public abstract String getDescription();
    abstract public void execute(final String ... params) throws Exception;
}
