package ru.karelin.tmclient.api.util;

public interface TerminalService {
    String readLn();
    char[] readPass();
}
