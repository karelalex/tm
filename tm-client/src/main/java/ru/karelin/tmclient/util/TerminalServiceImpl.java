package ru.karelin.tmclient.util;

import ru.karelin.tmclient.api.util.TerminalService;

import javax.enterprise.context.ApplicationScoped;
import java.io.Console;
import java.util.Scanner;

@ApplicationScoped
public class TerminalServiceImpl implements TerminalService {
    private Scanner scanner= new Scanner(System.in);
    private Console console = System.console();
    @Override
    public String readLn() {
        return scanner.nextLine();
    }

    @Override
    public char[] readPass() {
        return console.readPassword();
    }
}
