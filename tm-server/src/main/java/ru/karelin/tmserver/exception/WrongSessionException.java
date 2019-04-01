package ru.karelin.tmserver.exception;


public class WrongSessionException extends Exception {
    private static final long serialVersionUID = 2867839946315770980L;

    public WrongSessionException(String message) {
        super(message);
    }
}
