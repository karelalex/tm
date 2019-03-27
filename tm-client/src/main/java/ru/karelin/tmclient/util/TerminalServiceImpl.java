package ru.karelin.tmclient.util;

import ru.karelin.tm.api.util.TerminalService;

import java.io.Console;
import java.util.Scanner;

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
