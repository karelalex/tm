package ru.karelin.tmclient.command;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.util.ServiceLocator;
import ru.karelin.tm.api.util.TerminalService;


public abstract class AbstractCommand {

    @NotNull protected ServiceLocator locator;
    @NotNull protected TerminalService ts;

    protected boolean isSecured;

    public boolean isSecured() {
        return isSecured;
    }

    public AbstractCommand(@NotNull ServiceLocator locator, boolean isSecured) {
        this.locator = locator;
        ts= locator.getTerminalService();
        this.isSecured=isSecured;
    }

    public AbstractCommand(boolean isSecured) {
        this.isSecured=isSecured;
    }

    public void setLocator(@NotNull ServiceLocator locator) {
        this.locator = locator;
        this.ts = locator.getTerminalService();
    }
    public abstract String getName();
    public abstract String getDescription();
    abstract public void execute(final String ... params) throws Exception;
}
