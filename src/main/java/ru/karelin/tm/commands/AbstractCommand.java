package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;

import java.util.Scanner;

public abstract class AbstractCommand {

    protected Bootstrap bootstrap;
    protected Scanner sc;

    protected boolean isSecured;

    public boolean isSecured() {
        return isSecured;
    }

    public AbstractCommand(Bootstrap bootstrap, Boolean isSecured) {
        this.bootstrap = bootstrap;
        sc=bootstrap.getScanner();
        this.isSecured=isSecured;
    }

    public abstract String getName();
    public abstract String getDescription();
    abstract public void execute(String ... params);
}
