package ru.karelin.tmclient.util;

import org.springframework.stereotype.Component;
import ru.karelin.tmclient.api.util.TerminalService;

import java.io.Console;
import java.util.Scanner;

@Component
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
