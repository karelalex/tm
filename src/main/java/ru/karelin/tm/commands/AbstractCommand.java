package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;

import java.util.Scanner;

public abstract class AbstractCommand {
    private String name;
    private String description;
    protected Bootstrap bootstrap;
    protected Scanner sc;

    public AbstractCommand(String name, String description, Bootstrap bootstrap) {
        this.name = name;
        this.description = description;
        this.bootstrap = bootstrap;
        sc=bootstrap.getScanner();
    }

    public String getName(){
        return name;
    }
    public String getDecsription() {
        return description;
    }
    abstract public void execute(String ... params);
}
