package ru.karelin.tm.commands;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tm.api.ServiceLocator;

import java.util.Scanner;

public abstract class AbstractCommand {

    @NotNull protected ServiceLocator locator;
    @NotNull protected Scanner sc;

    protected boolean isSecured;

    public boolean isSecured() {
        return isSecured;
    }

    public AbstractCommand(@NotNull ServiceLocator locator, Boolean isSecured) {
        this.locator = locator;
        sc= locator.getScanner();
        this.isSecured=isSecured;
    }

    public abstract String getName();
    public abstract String getDescription();
    abstract public void execute(final String ... params);
}
