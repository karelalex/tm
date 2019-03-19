package ru.karelin.tm.commands;

import ru.karelin.tm.ServiceLocator;

import java.util.Scanner;

public abstract class AbstractCommand {

    protected ServiceLocator locator;
    protected Scanner sc;

    protected boolean isSecured;

    public boolean isSecured() {
        return isSecured;
    }

    public AbstractCommand(ServiceLocator locator, Boolean isSecured) {
        this.locator = locator;
        sc= locator.getScanner();
        this.isSecured=isSecured;
    }

    public abstract String getName();
    public abstract String getDescription();
    abstract public void execute(String ... params);
}
