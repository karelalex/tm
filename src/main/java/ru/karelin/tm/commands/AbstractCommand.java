package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;

import java.util.Scanner;

public abstract class AbstractCommand {

    protected Bootstrap bootstrap;
    protected Scanner sc;

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
        sc=bootstrap.getScanner();
    }

    public abstract String getName();
    public abstract String getDecsription();
    abstract public void execute(String ... params);
}
